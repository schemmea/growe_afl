package de.schemmea.ma


import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import groovy.util.logging.Slf4j
import org.junit.runner.RunWith

import java.nio.file.Path
import java.nio.file.Paths

@RunWith(JQF.class)
@Slf4j
class SimpleTest {

    @Fuzz
    void testNF() throws IOException {
        def appname = "nextflow"
        def home = System.getenv('NXF_HOME')
        Path result = home ? Paths.get(home) : Paths.get(System.getProperty("user.home")).resolve(".$appname")

        //fails in jar
        if (!result.exists() && !result.mkdir()) {
            throw new IllegalStateException("Cannot create path '${result}' -- check file system access permission")
        }

        //does not fail
        if (!result.toFile().exists() && !result.mkdir()) {
            throw new IllegalStateException("Cannot create path '${result}' -- check file system access permission")
        }


    }


}
