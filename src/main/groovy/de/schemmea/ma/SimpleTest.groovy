package de.schemmea.ma

import com.pholser.junit.quickcheck.From
import de.schemmea.ma.nf.UrlGenerator
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
        def result = home ? Paths.get(home) : Paths.get(System.getProperty("user.home")).resolve(".$appname")

        if (!result.exists() && !result.mkdir()) {
            throw new IllegalStateException("Cannot create path '${result}' -- check file system access permission")
        }


    }


}
