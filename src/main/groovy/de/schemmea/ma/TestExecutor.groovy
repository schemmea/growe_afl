package de.schemmea.ma

import com.beust.jcommander.JCommander
import de.schemmea.ma.utils.Args
import de.schemmea.ma.utils.Configuration
import de.schemmea.ma.utils.FileResourcesUtils
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing
import edu.berkeley.cs.jqf.fuzz.repro.ReproGuidance
import groovy.io.FileType
import java.util.stream.Collectors


class TestExecutor {
    static Args ARGS = new Args();

    static void main(String... args) {
        var commander = new JCommander(ARGS, args)
        String testname = "testAFL"
        Class testclass = NfAFLTest.class

        //  new FileResourcesUtils().copyFilesToFolder(Configuration.SOURCE_PATH, Configuration.OUTPUT_PATH);
        //  new FileResourcesUtils().copyFilesToFolder(Configuration.TEMPLATE_SOURCE_PATH, Configuration.OUTPUT_TEMPLATE_PATH);
        //  new FileResourcesUtils().copyFilesToFolder(Configuration.DATA_SOURCE_PATH, Configuration.OUTPUT_DATA_PATH);

        if (ARGS.guidance == "repro") {
            def testInputFiles = []
            new File(ARGS.reproDir).eachFile FileType.FILES, {
                testInputFiles << it
            }


            String traceDirName = System.getProperty("jqf.repro.traceDir");
            File traceDir = traceDirName != null ? new File(traceDirName) : null;

            ReproGuidance guidance = new ReproGuidance(testInputFiles as File[], traceDir)

            println "Repro $ARGS.reproDir"


            GuidedFuzzing.run(testclass, testname, guidance, System.out)

            //only works if started with javaagent and jqf script
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

