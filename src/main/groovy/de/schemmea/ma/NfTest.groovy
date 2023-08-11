package de.schemmea.ma

import com.pholser.junit.quickcheck.From
import com.pholser.junit.quickcheck.generator.java.lang.StringGenerator
import de.schemmea.ma.generator.Configuration
import de.schemmea.ma.generator.NextflowCommandGenerator
import de.schemmea.ma.generator.WorkflowFileGenerator
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import nextflow.cli.CmdRun
import nextflow.cli.Launcher
import nextflow.plugin.Plugins
import org.junit.After
import org.junit.Assume
import org.junit.Before
import org.junit.runner.RunWith

import static de.schemmea.ma.generator.Util.getFileName

@RunWith(JQF.class)
class NfTest {


    static int iteration = 0;


    @Before
    public void setup() {


    }

    @SuppressWarnings('DuplicatedCode')
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
    }

    @Fuzz
    public void testAFL(InputStream inputStream){
        /**
         * install afl
         * https://medium.com/@ayushpriya10/fuzzing-applications-with-american-fuzzy-lop-afl-54facc65d102
         * https://www.dannyvanheumen.nl/post/java-fuzzing-with-afl-and-jqf/
         */
        String filename =getFileName();
        File file = new File(filename);

        byte[] buffer = new byte[1024];

        try (OutputStream outputStream = new FileOutputStream(filename)) {
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            /**ignore**/
        }

        testWorkflow(file);
    }

    @Fuzz
    public void testNFCommand(@From(GroovyGenerator.class) String[] command) {

        println Configuration.newline + "ITERATION " + ++iteration + Configuration.newline
        println command

        if (command[0] == "run") {
            //avoid try catch (Throwable) in Launcher
            Launcher launcher = new Launcher().command(command)

            CmdRun myRunner = new CmdRun();
            myRunner.setArgs(command.tail().toList());
            myRunner.setLauncher(launcher);

            myRunner.run();

        } else {
            int status = new Launcher().command(command).run();
            println "status " + status
            Assume.assumeTrue(status==0)
        }
    }


    @Fuzz
    public void testTest(@From(StringGenerator.class) String inputFile) {
        print Configuration.newline + "ITERATION " + ++iteration + Configuration.newline

        try {
            String filename = "/home/alena/source/ma_test2/src/main/resources/test/out1691656718885.nf";
            String[] orig_args2 = new String[]{"run", filename};
            def args2 = [filename]

            int status = new Launcher().command(orig_args2).run();
            Assume.assumeTrue(status==0)

        } catch (Exception ex) {
            println "EXCEPTION"
            ex.printStackTrace()

        } catch (Error e) {
            println "ERROR"
            println e.getCause()
        }
        //nextflow clean ? <
    }

    @Fuzz
    public void testNFCommand2(@From(NextflowCommandGenerator.class) String[] command) {

        println Configuration.newline + "ITERATION " + ++iteration + Configuration.newline
        println command

        int status = new Launcher().command(command).run();
        println "status " + status

        Assume.assumeTrue(status==0)
    }


    @After
    public void cleanUp() {
        //plugins won't stop after sriptcompilation exception
        Plugins.stop()

        //nextflow clean -f
        int status = new Launcher().command(new String[]{"clean","-f"}).run();
    }

}

