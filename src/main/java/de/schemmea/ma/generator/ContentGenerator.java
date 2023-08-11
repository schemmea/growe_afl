package de.schemmea.ma.generator;

import com.pholser.junit.quickcheck.generator.Generator;
import com.sourceclear.gramtest.GeneratorVisitor;
import com.sourceclear.gramtest.bnfLexer;
import com.sourceclear.gramtest.bnfParser;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import de.schemmea.ma.utils.FileResourcesUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ContentGenerator extends Generator<String> {
    private static final int generateNumber = 1;
    private static final int depth = 4;
    private static final int max = 4;
    private static final int min = 1;

    List<String> scripts;

    private static final String magicstring = "scriptnamemagicstring";
    private static final String magicstring2 = "scriptname2magicstring";
    private static final String magicstring3 = "scriptplaceholder1magicstring";
    private static final String magicstring4 = "scriptplaceholder2magicstring";
    private static final String processcallsplaceholder = "prozessscallmagicstring";
    private static final String processcallsplaceholder2 = "prozesss2callmagicstring";
    private static final String channelnameone = "namedchannel1";
    private static final String channelnametwo = "namedchannel2";
    private final String processwithtwovars = "twovars";

    private final ParserRuleContext tree;

    public ContentGenerator() throws IOException {
        super(String.class);
        System.out.println("Generator - ctor");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        scripts = loadScriptFiles();
        InputStream bnfFile = new FileResourcesUtils().getResourceFileAsStream("/nextflow/bnfs/nextflow.bnf");

        Lexer lexer = new bnfLexer(new ANTLRInputStream(bnfFile));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tree = new bnfParser(tokens).rulelist();
    }

    public String generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {

        System.out.println("\nGenerator - generate");

        try {
            GeneratorVisitor extractor = new GeneratorVisitor(generateNumber, depth, min, max, true, sourceOfRandomness);
            extractor.visit(tree);
            List<String> generatedTests = extractor.getTests();

            String genTest = generatedTests.get(0);

            genTest = replaceProcesscallsPlaceholder(genTest, sourceOfRandomness);
            genTest = replaceMagicStringWithRandomScript(genTest, sourceOfRandomness);

            genTest = genTest.replace("\\n", Configuration.newline);

            return genTest;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    private String replaceProcesscallsPlaceholder(String testCase, SourceOfRandomness sourceOfRandomness) {
        String replaced = testCase;
        var processnames = collectProcessNames(replaced);
        var processnames2var = processnames.stream().filter(n -> n.contains(processwithtwovars)).collect(Collectors.toList());
        processnames.removeAll(processnames2var);

        /**
         * //idea: take first two single val processes and first twovar process and put them together with ch2.onew and ch2.two
         channel.of(...).set { channelname }

         process(channelname)

         channel.of(shfk).branch {one .., two ...}.set{ch2}
         process2(p1(ch2.one),p2(ch2.two))
         * **/

        String joinedProcesscalls = "";
        //tow variables
        String processcalls2 = "";

        if (processnames.size() > 2 && processnames2var.size() > 1) {
            joinedProcesscalls = processnames2var.remove(0) + "("
                    + processnames.remove(0) + "(" + channelnametwo + ".one), "
                    + processnames.remove(1) + "(" + channelnametwo + ".two))";
        }

        if (processnames2var.size() > 0) {
            processcalls2 = " | " + String.join(" | ", processnames2var) + "\n" + joinedProcesscalls;
        }
        replaced = replaced.replace(processcallsplaceholder2, Matcher.quoteReplacement(processcalls2));


        String processcalls = "";
        //one variable
        if (processnames.size() > 0) {
            processcalls += " | " + String.join(" | ", processnames);
        }
        replaced = replaced.replace(processcallsplaceholder, Matcher.quoteReplacement(processcalls));

        return replaced;
    }


    private String replaceMagicStringWithRandomScript(String testCase, SourceOfRandomness sourceOfRandomness) {
        String replaced = testCase;
        replaced = replaceScript(replaced, magicstring, s -> !s.contains(processwithtwovars), false, sourceOfRandomness);
        replaced = replaceScript(replaced, magicstring2, s -> s.contains(processwithtwovars), false, sourceOfRandomness);

        replaced = replaceScript(replaced, magicstring3, s -> !s.contains(processwithtwovars), true, sourceOfRandomness);
        replaced = replaceScript(replaced, magicstring4, s -> s.contains(processwithtwovars), true, sourceOfRandomness);


        return replaced;
    }

    private String replaceScript(String testCase, String scriptMagicString, Predicate<String> scriptFilter, boolean putFileContent, SourceOfRandomness sourceOfRandomness) {
        String replaced = testCase;
        var filteredScripts = scripts.stream().filter(scriptFilter).collect(Collectors.toList());
        if (filteredScripts.size() > 0) {
            int count = replaced.split(scriptMagicString).length - 1;
            for (int i = 0; i < count; i++) {
                //todo call script inline and not with template ? -> needs adaption in bnf

                String filename = sourceOfRandomness.choose(filteredScripts);
                if (putFileContent) {
                    try {
                        InputStream inputStream = new FileResourcesUtils().getFileFromResourceAsStream(Configuration.TEMPLATE_SOURCE_PATH + filename);
                        ByteArrayOutputStream result = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        for (int length; (length = inputStream.read(buffer)) != -1; ) {
                            result.write(buffer, 0, length);
                        }
                        // StandardCharsets.UTF_8.name() > JDK 7
                        String content = result.toString(StandardCharsets.UTF_8.name());

                        replaced = replaced.replaceFirst(scriptMagicString, Matcher.quoteReplacement(content));
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    replaced = replaced.replaceFirst(scriptMagicString, Matcher.quoteReplacement(filename));
                }
            }
        }
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