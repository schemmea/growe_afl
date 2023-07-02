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
import org.codehaus.groovy.ast.expr.BooleanExpression;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkflowGenerator extends Generator<File> {
    private File generated;

    StringGenerator baseGenerator;

    public WorkflowGenerator() throws IOException {
        super(File.class);

        baseGenerator = new StringGenerator();

        generated = new File(Paths.get(Configuration.OUTPUT_PATH).toUri());
        if (!generated.exists()) {
            generated.mkdir();
        }
    }

    @Override
    public File generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        File file = new File(getFileName());

        try {
            String genTest = baseGenerator.generate(sourceOfRandomness, generationStatus);

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

    private String getFileName() {
        long date = System.currentTimeMillis();

        return generated.getAbsolutePath() + "/out" + date + ".nf";
    }


}