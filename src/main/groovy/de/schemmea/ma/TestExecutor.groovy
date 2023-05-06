package de.schemmea.ma

import de.schemmea.ma.utils.FileResourcesUtils
import edu.berkeley.cs.jqf.fuzz.ei.*
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance
import edu.berkeley.cs.jqf.fuzz.junit.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.Duration

class TestExecutor {

    static void main(String... args) {

        String testname = "testNF"
        long durationSeconds = 300
        long trials = 10
        String errorDir = "errorDir"
        Class testclass = NfTest.class
        //Class testclass = SimpleTest.class

        println "" //empty line because of jqf
        println "Testing $testclass.name # $testname $trials times, duration: $durationSeconds s"

        File errorDirectory = Paths.get(errorDir).toFile();
        if (!errorDirectory.exists()) {
            errorDirectory.mkdir();
        }

        copyTemplates()

        Guidance guidance = new ZestGuidance(testname,
                Duration.ofSeconds(durationSeconds),
                trials,
                errorDirectory,
                new Random())

        GuidedFuzzing.run(testclass, testname, guidance, System.out)
        println "Testing $testclass.name#$testname $trials times, duration: $durationSeconds s"
    }

    private static void copyTemplates() {
        new FileResourcesUtils().copyFilesToFolder("templates", "generatedflows/templates");
    }
}
