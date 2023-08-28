package de.schemmea.ma

import com.beust.jcommander.JCommander
import de.schemmea.ma.utils.Args
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing
import edu.berkeley.cs.jqf.fuzz.repro.ReproGuidance


class TestExecutor {
    static Args ARGS = new Args();

    static void main(String... args) {
        var commander = new JCommander(ARGS, args)
        String testname = "testAFL"
        Class testclass = NfAFLTest.class

        if (ARGS.guidance == "repro") {
            File[] testInputFiles = new File(ARGS.reproDir).listFiles().sort();

            String traceDirName = System.getProperty("jqf.repro.traceDir");
            File traceDir = traceDirName != null ? new File(traceDirName) : null;

            ReproGuidance guidance = new ReproGuidance(testInputFiles, traceDir)

            println "Repro $ARGS.reproDir"


            GuidedFuzzing.run(testclass, testname, guidance, System.out)


            if (guidance.getBranchesCovered() != null) {
                String cov = "";
                for (String s : guidance.getBranchesCovered()) {
                    cov += "# Covered: " + s + "\n";
                }
                final String finalFooter = cov;
                System.out.println(finalFooter);
            }

            System.out.println(String.format("Covered %d edges.", guidance.getCoverage().getNonZeroCount()));


        } else {

            println "Debugging jar $testclass.name#$testname AFL - calling groovy "
        }
    }

}

