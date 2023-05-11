package de.schemmea.ma

import de.schemmea.ma.utils.FileResourcesUtils
import edu.berkeley.cs.jqf.fuzz.ei.*
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance
import edu.berkeley.cs.jqf.fuzz.junit.*

import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.time.Duration

class TestExecutor {
  public  static final String formattedDate = new Date().format("yyyy-MM-dd_hh-mm-ss");

    static void main(String... args) {

        String testname = "testNF"
        long durationSeconds = 60
        long trials = 50
        String errorDir = "$formattedDate/errorDir"
        Class testclass = NfTest.class
        //Class testclass = SimpleTest.class

        println "Testing $testclass.name # $testname $trials times, duration: $durationSeconds s"
        println "Working Directory for generated tests: $formattedDate"


        File errorDirectory = Paths.get(errorDir).toFile();
        if (!errorDirectory.exists()) {
            errorDirectory.mkdir();
        }
        copyTemplates(formattedDate)

        Guidance guidance = new ZestGuidance(testname,
                Duration.ofSeconds(durationSeconds),
                trials,
                errorDirectory,
                new Random())

        GuidedFuzzing.run(testclass, testname, guidance, System.out)
        println "Testing $testclass.name#$testname $trials times, duration: $durationSeconds s"
    }

    private static void copyTemplates(formattedDate) {
        def newTemplatePath = "$formattedDate/generatedflows/templates"

        new FileResourcesUtils().copyFilesToFolder("templates", newTemplatePath);
    }
}
