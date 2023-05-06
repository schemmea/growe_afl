package de.schemmea.ma

import com.pholser.junit.quickcheck.random.SourceOfRandomness
import de.schemmea.ma.generator.UrlGenerator
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.NonTrackingGenerationStatus
import nextflow.cli.Launcher


class FooBar {


    FooBar(){}

    void launchOneScript() {
        var startTime = System.currentTimeMillis();

        File file = new File(getClass().getResource("/scripts/yesOrNo.nf").toExternalForm())

        String[] args2 = ["run", file.path]
        final status = new Launcher().command(args2).run()
        println "launched nextflow"
        if (status) {
            var stopTime = System.currentTimeMillis();

            println "took " + (stopTime - startTime) / 1000 + "s"
            System.exit(status)
        }
    }

    void launchWithGenerator() {

        var startTime = System.currentTimeMillis();
        UrlGenerator gen = new UrlGenerator()
        var rand = new SourceOfRandomness(new Random())
        var inputString = gen.generate(rand, new NonTrackingGenerationStatus(rand))

        println inputString

        File file = new File("out" + ".nf")
        file.write inputString

        String[] args2 = ["run", file.path]
        final status = new Launcher().command(args2).run()
        println "launched nextflow"
        if (status) {
            var stopTime = System.currentTimeMillis();

            println "took " + (stopTime - startTime) / 1000 + "s"
            System.exit(status)
        }
    }

}
