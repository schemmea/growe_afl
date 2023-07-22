package de.schemmea.ma.generator;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.io.*;
import java.nio.file.Paths;

public class WorkflowFileGenerator extends Generator<File> {

    ContentGenerator baseGenerator;

    public WorkflowFileGenerator() throws IOException {
        super(File.class);

        baseGenerator = new ContentGenerator();
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
        File generated = new File(Paths.get(Configuration.OUTPUT_PATH).toUri());
        if (!generated.exists()) {
            generated.mkdir();
        }

        return generated.getAbsolutePath() + "/out" + date + ".nf";
    }


}