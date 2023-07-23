package de.schemmea.ma

import com.pholser.junit.quickcheck.From
import de.schemmea.ma.generator.Configuration
import de.schemmea.ma.generator.NextflowCommandGenerator
import de.schemmea.ma.generator.WorkflowFileGenerator
import de.schemmea.ma.utils.FileResourcesUtils
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import nextflow.cli.CmdRun
import nextflow.cli.Launcher
import nextflow.plugin.Plugins
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(JQF.class)
class NfTest {


    static int iteration = 0;


    @Before
    public void setup() {


    }

    @Fuzz
    public void testWorkflow(@From(WorkflowFileGenerator.class) File inputFile) {
        print Configuration.newline + "ITERATION " + ++iteration + Configuration.newline
        String filename = inputFile.getAbsolutePath();
        String[] orig_args2 = new String[]{"run", filename};
        def args2 = [filename]

        Launcher launcher = new Launcher().command(orig_args2)//.run();

        CmdRun myRunner = new CmdRun();
        myRunner.setArgs(args2);
        myRunner.setLauncher(launcher);

        myRunner.run();

        //nextflow clean ? <
    }


    @Fuzz
    public void testNFCommand(@From(NextflowCommandGenerator.class) String[] command) {

        print Configuration.newline + "ITERATION " + ++iteration + Configuration.newline
        print command

        if (command[0] == "run") {
            //avoid try catch in Launcher
            Launcher launcher = new Launcher().command(command)

            CmdRun myRunner = new CmdRun();
            myRunner.setArgs(command.tail().toList());
            myRunner.setLauncher(launcher);

            myRunner.run();

        } else {
            int status = new Launcher().command(command).run();
            print "status " + status
        }
    }

    @After
    public void cleanUp() {
        Plugins.stop()
    }

}

