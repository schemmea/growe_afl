package de.schemmea.ma

import com.beust.jcommander.JCommander
import de.schemmea.ma.generator.Configuration
import de.schemmea.ma.guidance.FileAwareZestGuidance
import de.schemmea.ma.utils.Args
import edu.berkeley.cs.jqf.fuzz.ei.ZestGuidance
import edu.berkeley.cs.jqf.fuzz.guidance.Guidance
import edu.berkeley.cs.jqf.fuzz.guidance.Result
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing
import org.apache.commons.lang.StringUtils

import java.nio.file.Paths
import java.time.Duration

class BaselineTestExecutor {

    private static Args ARGS = new Args();
    private static Set<String> stackTraces = new HashSet<>();
    private static Map<String, Integer> nameCountMap = new HashMap<>();

    static File logfile = new File(Paths.get(Configuration.EXCEPTION_LOG_FILE).toUri());

    static void main(String... args) {

        var commander = new JCommander(ARGS)
        commander.parse(args);
        commander.setProgramName("TestingNFBaseline")

        String testname = "testBaseline"
        Class testclass = BaselineNfTest.class

        String errorDir = Configuration.ERROR_DIR;

        boolean iswindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (iswindows && !errorDir.startsWith("C:") && !errorDir.startsWith("/")) errorDir = "/" + errorDir;

        println "Testing $testclass.name # $testname $ARGS.iteration times, duration: $ARGS.durationInSeconds s"

        File errorDirectory = Paths.get(errorDir).toFile();
        if (!errorDirectory.exists()) {
            errorDirectory.mkdir();
        }

        Guidance guidance = new ZestGuidance(testname,
                Duration.ofSeconds(ARGS.durationInSeconds),
                ARGS.iteration,
                errorDirectory,
                new Random())

        GuidedFuzzing.run(testclass, testname, guidance, System.out)

        System.out.println(String.format("Covered %d edges.", guidance.getTotalCoverage().getNonZeroCount()));
        println "Tested $testclass.name#$testname $ARGS.iteration times, duration: $ARGS.durationInSeconds s"
    }

}

