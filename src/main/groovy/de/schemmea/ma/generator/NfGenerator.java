package de.schemmea.ma.generator;

import com.sourceclear.gramtest.GeneratorVisitor;
import com.sourceclear.gramtest.bnfLexer;
import com.sourceclear.gramtest.bnfParser;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import de.schemmea.ma.utils.Configuration;
import de.schemmea.ma.utils.FileResourcesUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

public class NfGenerator extends Generator<String> {
    private final int generateNumber = 1;
    private final int depth = 4;
    private final int max = 4;
    private final int min = 1;

    final BlockingQueue<String> queue = new SynchronousQueue<>();
    InputStream file = null;
    Lexer lexer = null;
    List<String> scripts;

    private final String magicstring = "scriptnamemagicstring";
    private final String magicstring2 = "scriptname2magicstring";
    private final String processcallsplaceholder = "prozessscallmagicstring";
    private final String processcallsplaceholder2 = "prozesss2callmagicstring";

    private final String processwithtwovars = "twovars";

    public NfGenerator() {
        super(String.class);
        System.out.println("Generator - ctor");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        scripts = loadScriptFiles();
    }

    @Override
    public String generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {

        System.out.println("Generator - generate");

        try {
            file = new FileResourcesUtils().getResourceFileAsStream("/nextflow/bnfs/nextflow.bnf");

            lexer = new bnfLexer(new ANTLRInputStream(file));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ParserRuleContext tree = new bnfParser(tokens).rulelist();

            GeneratorVisitor extractor = new GeneratorVisitor(generateNumber, depth, min, max, true, sourceOfRandomness);
            extractor.visit(tree);
            List<String> generatedTests = extractor.getTests();

            String genTest = generatedTests.get(0);

            return replaceMagicStringWithRandomScript(genTest, sourceOfRandomness);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    private String replaceScript(String testCase, String scriptMagicString, String scriptFilter, SourceOfRandomness sourceOfRandomness) {
        String replaced = testCase;
        var filteredScripts = scripts.stream().filter(s->s.contains(scriptFilter)).collect(Collectors.toList());
        if (filteredScripts.size() > 0) {
            int count = replaced.split(scriptMagicString).length - 1;
            for (int i = 0; i < count; i++) {
                int template = new Random().nextInt(filteredScripts.size()); //if end of randomness - should not happen

                try {
                    template = sourceOfRandomness.nextInt(filteredScripts.size());
                } catch (Exception e) {
                    System.out.println("dafuq - end of random");
                }
                String filename = filteredScripts.get(template);
                replaced = replaced.replaceFirst(scriptMagicString, filename);
            }
        }
        return replaced;
    }

    private String replaceMagicStringWithRandomScript(String testCase, SourceOfRandomness sourceOfRandomness) {
        String replaced = testCase;
        replaced = replaceScript(replaced, magicstring, "", sourceOfRandomness);
        replaced = replaceScript(replaced, magicstring2, processwithtwovars,sourceOfRandomness);

        var processnames = collectProcessNames(replaced);
        var processnames2var = processnames.stream().filter(n -> n.contains(processwithtwovars)).collect(Collectors.toList());
        processnames.removeAll(processnames2var);

        String processcalls = "";
        //one variable
        if (processnames.size() > 0) processcalls += " | "+ String.join(" | ", processnames);
        replaced = replaced.replace(processcallsplaceholder, processcalls);

        //tow variables
        String processcalls2 = "";
        if (processnames2var.size() > 0) {
            processcalls2 +=" | "+ String.join(" | ", processnames2var);
        }
        replaced = replaced.replace(processcallsplaceholder2, processcalls2);


        return replaced;
    }

    private List<String> collectProcessNames(String testcase) {
        List<String> processnames = new ArrayList<>();

        String[] split1 = testcase.split("process");

        for (int i = 1; i < split1.length; i++) {
            processnames.add(split1[i].split("\\{")[0]);
        }

        return processnames;

    }


    private List<String> loadScriptFiles() {
        return new FileResourcesUtils().getResourceFiles(Configuration.TEMPLATE_SOURCE_PATH);
    }


}