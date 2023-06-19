package de.schemmea.ma

import de.schemmea.ma.utils.Configuration
import de.schemmea.ma.utils.FileResourcesUtils
import edu.berkeley.cs.jqf.fuzz.ei.*
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance
import edu.berkeley.cs.jqf.fuzz.junit.*

import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.time.Duration

class TestExecutor {

    static void main(String... args) {

        String testname = "testNF"
        long durationSeconds = 3600
        long trials = 5

        Class testclass = NfTest.class
        //Class testclass = SimpleTest.class

        String errorDir = "errorDir"

        boolean iswindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (iswindows && !errorDir.startsWith("C:") && !errorDir.startsWith("/")) errorDir = "/" + errorDir;

        println "Testing $testclass.name # $testname $trials times, duration: $durationSeconds s"
       // println "Working Directory for generated tests: $Configuration.formattedDate"


        File errorDirectory = Paths.get(errorDir).toFile();
        if (!errorDirectory.exists()) {
            errorDirectory.mkdir();
        }

        Guidance guidance = new ZestGuidance(testname,
                                             Duration.ofSeconds( durationSeconds),
                                             errorDirectory)

        GuidedFuzzing.run(testclass, testname, guidance, System.out)
        System.out.println(String.format("Covered %d edges.", guidance.getTotalCoverage().getNonZeroCount()));
        println "Testing $testclass.name#$testname $trials times, duration: $durationSeconds s"
    }

}
