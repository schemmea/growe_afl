package de.schemmea.ma.nf;

import com.gramtest.GeneratorVisitor;
import com.gramtest.bnfLexer;
import com.gramtest.bnfParser;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class NfGenerator extends Generator<String> {
    private final int generateNumber = 4;
    private final int depth = 2;
    private final int max = 4;
    private final int min = 1;

    final BlockingQueue<String> queue = new SynchronousQueue<>();
    InputStream file = null;
    Lexer lexer = null;
    List<String> scripts;

    private String magicString = "382z9dfiusdpuzn5934magicstring9834n945zp9";

    public NfGenerator() {
        super(String.class);
        scripts = loadScriptFiles();
    }

    @Override
    public String generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        try {
            file = getClass().getResourceAsStream("/bnfs/nextflow.bnf");

            lexer = new bnfLexer(org.antlr.v4.runtime.CharStreams.fromStream(file));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ParserRuleContext tree = new bnfParser(tokens).rulelist();

            GeneratorVisitor extractor = new GeneratorVisitor(generateNumber, depth, min, max, true);
            extractor.visit(tree);
            List<String> generatedTests = extractor.getTests();

            int idx = generatedTests.size() > 1 ? sourceOfRandomness.nextInt(0, generatedTests.size() - 1) : 0;
            String genTest = generatedTests.get(idx);


            return replaceMagicStringWithRandomScript(genTest, sourceOfRandomness);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String replaceMagicStringWithRandomScript(String testCase, SourceOfRandomness sourceOfRandomness) {
        var filename = scripts.get(sourceOfRandomness.nextInt(scripts.size() ));


        String replaced = testCase.replaceFirst(magicString, filename);
        int count = replaced.split(magicString).length - 1;
        for (int i = 0; i < count; i++) {
            replaced = replaced.replaceFirst(magicString, filename);
        }

        String processcalls = String.join(" | " , collectProcessNames(replaced))+"";

        //todo reset workflow to actual processes
        replaced = replaced.replace("spaceholder", processcalls);

        return replaced;
    }

    private List<String> collectProcessNames( String testcase) {
        List<String> processnames = new ArrayList<>();

        var split1 = testcase.split("process");

        for (int i = 1; i <  split1.length; i++) {
          processnames.add(  split1[i].split("\\{")[0]);
        }

        return processnames;

    }
    private List<String> loadScriptFiles() {

        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getClass().getResourceAsStream("/scripts");
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filenames;
    }
}
