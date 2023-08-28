package de.schemmea.ma;

import com.beust.jcommander.JCommander;
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing;
import edu.berkeley.cs.jqf.fuzz.repro.ReproGuidance;

import java.io.File;
import java.io.IOException;

class TestExecutor {
    static Args ARGS = new Args();

    public static void main(String... args) throws IOException {
        var commander = new JCommander(ARGS, args);

        /**dummy main class for building jar**/

        String testname = "testAFL";
        Class testclass = NfAFLTest.class;

        if (ARGS.getGuidance().equals("repro")) {
            testname = "testAFLRepro";
            File[] testInputFiles = new File(ARGS.getReproDir()).listFiles(File::isFile);

            String traceDirName = System.getProperty("jqf.repro.traceDir");
            File traceDir = traceDirName != null ? new File(traceDirName) : null;

            ReproGuidance guidance = new ReproGuidance(testInputFiles, traceDir);

            System.out.println("Repro" + ARGS.getReproDir());


            /* ~~~~~~~~~~~~~~~ execution ~~~~~~~~~~~~~~~~ */
            GuidedFuzzing.run(testclass, testname, guidance, System.out);

            if (guidance.getBranchesCovered() != null) {
                String cov = "";
                for (String s : guidance.getBranchesCovered()) {
                    cov += "# Covered: " + s + "\n";
                }
                final String finalFooter = cov;
                System.out.println(finalFooter);
            }

            System.out.println(String.format("Covered %d edges.", guidance.getCoverage().getNonZeroCount()));

        }

        System.out.println("Testing" + testclass.getName() + "#" + testname + " with AFL");
    }

}

