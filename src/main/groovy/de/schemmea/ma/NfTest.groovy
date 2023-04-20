package de.schemmea.ma

import com.pholser.junit.quickcheck.From
import de.schemmea.ma.nf.NfGenerator
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import nextflow.cli.Launcher
import org.junit.runner.RunWith

@RunWith(JQF.class)
class NfTest {


    static int iteration = 0;

    @Fuzz
    void testNF(@From(NfGenerator.class) String inputFile) throws IOException {
        var newline = System.getProperty("line.separator");

        inputFile = inputFile.replace("\\n", newline)

        println newline + "STARTING ITERATION " + (++iteration) + newline + inputFile

        var date = System.currentTimeMillis()
        date -= 1682000000000

        File file = new File("generatedflows/out"+date+".nf")
        file.write inputFile

        String[] args2 = ["run", file.path]
        final status = new Launcher().command(args2).run()
        println "launched nextflow"
    }

    @Fuzz
    void testSimple() {
        var startTime = System.currentTimeMillis();

        File file = new File(getClass().getResource("/scripts/yesOrNo.nf").toURI())

        String[] args2 = ["run", file.absolutePath]
        final status = new Launcher().command(args2).run()
        println "launched nextflow"
        if (status) {
            var stopTime = System.currentTimeMillis();

            println "took " + (stopTime - startTime) / 1000 + "s"
            System.exit(status)
        }
    }

}
