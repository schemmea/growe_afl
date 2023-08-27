package de.schemmea.ma;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import nextflow.cli.CmdRun;
import nextflow.cli.Launcher;
import nextflow.plugin.Plugins;
import org.junit.After;
import org.junit.Assume;
import org.junit.runner.RunWith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RunWith(JQF.class)
public class NfAFLTest {

    public static String getFileName() {
        long date = System.currentTimeMillis();
        File generated = new File(Paths.get(Configuration.OUTPUT_PATH).toUri());
        if (!generated.exists()) {
            generated.mkdir();
        }

        return generated.getAbsolutePath() + "/out" + date + ".nf";
    }

    private void serializeInputStream(InputStream instream, String filename) throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter out = Files.newBufferedWriter(path)) {
            int b;
            while ((b = instream.read()) != -1) {
                out.write(b);
            }
        }
    }

    static int iteration = 0;

    @Fuzz
    public void testAFL(InputStream inputStream) throws IOException {

        System.out.println("+++++ ITERATION " + ++iteration + "+++++");
        /*
         * install afl
         * https://medium.com/@ayushpriya10/fuzzing-applications-with-american-fuzzy-lop-afl-54facc65d102
         * https://www.dannyvanheumen.nl/post/java-fuzzing-with-afl-and-jqf/
         */
        String filename = getFileName();
        try {
            serializeInputStream(inputStream, filename);

            List<String> args2 = List.of(filename);
            String[] orig_args2 = new String[]{"run", filename};

            Launcher launcher = new Launcher().command(orig_args2);//.run();

            CmdRun myRunner = new CmdRun();
            myRunner.setArgs(args2);
            myRunner.setLauncher(launcher);

            myRunner.run();

            Thread.sleep(1000); //1sec

            clean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable t) {
            Assume.assumeNoException(t);
        } finally {

            //instead of @After
            Files.delete(Paths.get(filename));

        }

    }


    public void clean() throws IOException {
        System.out.println("+++++ CLEANING " + iteration + "+++++");
        Plugins.stop();
        Files.delete(Paths.get(System.getProperty("user.dir"), ".nextflow.log"));
        //nextflow clean does not work?!
        int status = new Launcher().command(new String[]{"clean", "-f"}).run();

    }

    @Fuzz
    public void debugTest() throws IOException {
        String filename = "/home/alena/source/ma_test2/src/main/resources/seeds/yesOrNo.nf";
        try {

            List<String> args2 = List.of(filename);
            String[] orig_args2 = new String[]{"run", filename};

            Launcher launcher = new Launcher().command(orig_args2);//.run();

            CmdRun myRunner = new CmdRun();
            myRunner.setArgs(args2);
            myRunner.setLauncher(launcher);

            myRunner.run();
        } catch (Throwable t) {
            Assume.assumeNoException(t);
        } finally {

        }
    }
}
