package de.schemmea.ma

import com.pholser.junit.quickcheck.From
import de.schemmea.ma.nf.UrlGenerator
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import groovy.util.logging.Slf4j
import org.junit.runner.RunWith

@RunWith(JQF.class)
@Slf4j
class SimpleTest {

    @Fuzz
    void testNF(@From(UrlGenerator.class) String inputFile) throws IOException {
        println inputFile
    }


}
