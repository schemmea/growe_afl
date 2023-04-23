package de.schemmea.ma.nf;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.sourceclear.gramtest.GeneratorVisitor;
import com.sourceclear.gramtest.bnfLexer;
import com.sourceclear.gramtest.bnfParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class UrlGenerator extends Generator<String> {

    private ParserRuleContext tree;
    private GeneratorVisitor extractor;
    private int generateNumber = 1;
    private int depth = 2;
    private int max = 4;
    private int min = 1;

    final BlockingQueue<String> queue = new SynchronousQueue<>();

   public UrlGenerator() throws URISyntaxException, IOException {
        super(String.class);
        InputStream file = getClass().getResourceAsStream("/bnfs/url.bnf");

        Lexer lexer = new bnfLexer(org.antlr.v4.runtime.CharStreams.fromStream(file));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        this.tree = new bnfParser(tokens).rulelist();

    }

    @Override
    public String generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        extractor = new GeneratorVisitor(generateNumber, depth, min, max, true);
        extractor.visit(tree);
        List<String> generatedTests = extractor.getTests();

        return generatedTests.get(0);
    }
}
