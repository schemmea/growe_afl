package de.schemmea.ma

import com.pholser.junit.quickcheck.From
import de.schemmea.ma.generator.Configuration
import de.schemmea.ma.generator.ContentGenerator
import de.schemmea.ma.generator.WorkflowFileGenerator
import edu.berkeley.cs.jqf.fuzz.Fuzz
import edu.berkeley.cs.jqf.fuzz.JQF
import nextflow.cli.CmdRun
import nextflow.cli.Launcher
import org.junit.runner.RunWith

@RunWith(JQF.class)
class TestGenerator {

    @Fuzz
    public void testGen(@From(ContentGenerator.class) String inputFile) {
        print inputFile
    }

}
