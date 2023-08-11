package de.schemmea.ma.nf

import nextflow.Const
import nextflow.cli.CmdInfo
import nextflow.cli.CmdRun
import nextflow.cli.Launcher
import nextflow.config.ConfigBuilder
import nextflow.exception.AbortOperationException
import nextflow.plugin.Plugins
import nextflow.script.ScriptRunner
import nextflow.secret.SecretsLoader
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CustomNextflowScriptRunner extends CmdRun {

    private static final Logger log
            = LoggerFactory.getLogger(CmdRun.class);

    public CustomNextflowScriptRunner(List<String> args) {
        super()
        this.args = args;
        Launcher launcher = new Launcher().command((String[]) args.toArray())
        setLauncher(launcher)


    }

    @Override
    void run() {
        final scriptArgs = (args?.size() > 1 ? args[1..-1] : []) as List<String>
        final pipeline = stdin ? '-' : (args ? args[0] : null)
        if (!pipeline)
            throw new AbortOperationException("No project name was specified")

        if (withPodman && withoutPodman)
            throw new AbortOperationException("Command line options `-with-podman` and `-without-podman` cannot be specified at the same time")

        if (withDocker && withoutDocker)
            throw new AbortOperationException("Command line options `-with-docker` and `-without-docker` cannot be specified at the same time")

        if (withConda && withoutConda)
            throw new AbortOperationException("Command line options `-with-conda` and `-without-conda` cannot be specified at the same time")

        if (withSpack && withoutSpack)
            throw new AbortOperationException("Command line options `-with-spack` and `-without-spack` cannot be specified at the same time")

        if (offline && latest)
            throw new AbortOperationException("Command line options `-latest` and `-offline` cannot be specified at the same time")

        if (dsl1 && dsl2)
            throw new AbortOperationException("Command line options `-dsl1` and `-dsl2` cannot be specified at the same time")

        checkRunName()

        log.info "N E X T F L O W  ~  version ${Const.APP_VER}"
        try {
            Plugins.init()
        }
        catch (Exception e) {
            log.info(e.message)
        }
        log.info('Plugins init')

        // -- specify the arguments
        final scriptFile = getScriptFile(pipeline)
        log.info('got script file')

        // create the config object
        final builder = new ConfigBuilder()
                .setOptions(launcher.options)
                .setCmdRun(this)
                .setBaseDir(scriptFile.parent)
        final config = builder.build()

        log.info('built config')

        // check DSL syntax in the config
        launchInfo(config, scriptFile)
        log.info('launched info')

        // check if NXF_ variables are set in nextflow.config
        checkConfigEnv(config)
        log.info('check config')
        // -- load plugins
        final cfg = plugins ? [plugins: plugins.tokenize(',')] : config
        Plugins.load(cfg)
        log.info('loaded plugins')

        // -- load secret provider
        if (SecretsLoader.isEnabled()) {
            final provider = SecretsLoader.instance.load()
            config.withSecretProvider(provider)
        }
        log.info('secrets loaded')

        // -- create a new runner instance
        final runner = new ScriptRunner(config)
        runner.setScript(scriptFile)
        runner.setPreview(this.preview)
        runner.session.profile = profile
        runner.session.commandLine = launcher.cliString
        runner.session.ansiLog = launcher.options.ansiLog
        runner.session.disableJobsCancellation = getDisableJobsCancellation()
        log.info('session build')

        final isTowerEnabled = config.navigate('tower.enabled') as Boolean
        if (isTowerEnabled || log.isTraceEnabled())
            runner.session.resolvedConfig = ConfigBuilder.resolveConfig(scriptFile.parent, this)
        // note config files are collected during the build process
        // this line should be after `ConfigBuilder#build`
        runner.session.configFiles = builder.parsedConfigFiles
        // set the commit id (if any)
        runner.session.commitId = scriptFile.commitId
        if (this.test) {
            runner.test(this.test, scriptArgs)
            return
        }

        def info = CmdInfo.status(log.isTraceEnabled())
        log.info('\n' + info)

        // -- add this run to the local history
        runner.verifyAndTrackHistory(launcher.cliString, runName)

        // -- run it!
        runner.execute(scriptArgs, this.entryName)
        log.info('started execution')

     // runner.session.destroy()
     // runner.session.cleanup();
    }
}
