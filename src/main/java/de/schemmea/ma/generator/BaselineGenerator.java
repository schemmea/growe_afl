package de.schemmea.ma.generator;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.sourceclear.gramtest.GeneratorVisitor;
import com.sourceclear.gramtest.bnfLexer;
import com.sourceclear.gramtest.bnfParser;
import de.schemmea.ma.utils.FileResourcesUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.*;
import java.util.List;

import static de.schemmea.ma.generator.Util.getFileName;

public class BaselineGenerator extends Generator<File> {
    private static final int generateNumber = 1;
    private static final int depth = 2;
    private static final int max = 2;
    private static final int min = 1;

    private final ParserRuleContext tree;


    public BaselineGenerator() throws IOException {
        super(File.class);

        System.out.println("Generator - ctor");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        InputStream bnfFile = new FileResourcesUtils().getResourceFileAsStream("/nextflow/bnfs/nextflow_baseline.bnf");

        Lexer lexer = new bnfLexer(new ANTLRInputStream(bnfFile));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tree = new bnfParser(tokens).rulelist();

    }


    public File generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {

        System.out.println("\nGenerator - generate");

        File file = new File(getFileName());

        try {
            GeneratorVisitor extractor = new GeneratorVisitor(generateNumber, depth, min, max, true, sourceOfRandomness);
            extractor.visit(tree);
            List<String> generatedTests = extractor.getTests();

            String genTest = generatedTests.get(0);
            genTest = genTest.replace("\\n", Configuration.newline);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(genTest);
            writer.close();

            return file;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
