package de.schemmea.ma.nf

import nextflow.cli.Launcher
import nextflow.exception.AbortOperationException
import nextflow.exception.AbortRunException
import nextflow.exception.ConfigParseException
import nextflow.exception.ScriptCompilationException
import nextflow.exception.ScriptRuntimeException
import org.eclipse.jgit.api.errors.GitAPIException

class CustomNextflowLauncher extends Launcher{
    @Override
    /**
     * Launch the pipeline execution
     */
    int run() {

        /*
         * setup environment
         */
        setupEnvironment()

        /*
         * Real execution starts here
         */
        try {
            log.debug '$> ' + cliString

            // -- print out the version number, then exit
            if ( options.version ) {
                println getVersion(fullVersion)
                return 0
            }

            // -- print out the program help, then exit
            checkForHelp()

            // launch the command
            (new CustomNextflowScriptRunner())?.run()

            if( log.isTraceEnabled())
                log.trace "Exit\n" + dumpThreads()
            return 0
        }

        catch( AbortRunException e ) {
            return(1)
        }

        catch ( AbortOperationException e ) {
            def message = e.getMessage()
            if( message ) System.err.println(message)
            log.debug ("Operation aborted", e.cause ?: e)
            return(1)
        }

        catch ( GitAPIException e ) {
            System.err.println e.getMessage() ?: e.toString()
            log.debug ("Operation aborted", e.cause ?: e)
            return(1)
        }

        catch( ConfigParseException e )  {
            def message = e.message
            if( e.cause?.message ) {
                message += "\n\n${e.cause.message.toString().indent('  ')}"
            }
            log.error(message, e.cause ?: e)
            return(1)
        }

        catch( ScriptCompilationException e ) {
            log.error(e.message, e)
            return(1)
        }

        catch ( ScriptRuntimeException | IllegalArgumentException e) {
            log.error(e.message, e)
            return(1)
        }

        catch( IOException e ) {
            log.error(e.message, e)
            return(1)
        }

        catch( Throwable fail ) {
            log.error("@unknown", fail)
            return(1)
        }

    }
}
