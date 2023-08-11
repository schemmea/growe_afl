package de.schemmea.ma.generator;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.io.*;

import static de.schemmea.ma.generator.Util.getFileName;

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


}