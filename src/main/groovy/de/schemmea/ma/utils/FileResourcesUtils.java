package de.schemmea.ma.utils;

import javax.accessibility.AccessibleTableModelChange;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileResourcesUtils {

    public static void main(String[] args) throws IOException {

        FileResourcesUtils app = new FileResourcesUtils();

        // Sample 3 - read all files from a resources folder (JAR version)
        try {

            // get paths from src/main/resources/json
            List<Path> result = app.getPathsFromResourceJAR("json");
            for (Path path : result) {
                System.out.println("Path : " + path);

                String filePathInJAR = path.toString();
                // Windows will returns /json/file1.json, cut the first /
                // the correct path should be json/file1.json
                if (filePathInJAR.startsWith("/")) {
                    filePathInJAR = filePathInJAR.substring(1, filePathInJAR.length());
                }

                System.out.println("filePathInJAR : " + filePathInJAR);

                // read a file from resource folder
                InputStream is = app.getFileFromResourceAsStream(filePathInJAR);
                printInputStream(is);
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getResourceFiles(String folder) {
        try {
            if (FileResourcesUtils.runningFromJar()) {
                // run in jar
                return getFileNamesFromFolderInJar(folder);
            } else {
                // run in ide
                return getFileNamesFromFolder(folder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static String getJarName() {
        try {
            return new File(FileResourcesUtils.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath())
                    .getName();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        return "";
        }
    }

    public static boolean runningFromJar() {
        var jarname = getJarName();

        return jarname.contains(".jar");
    }

    private List<String> getFileNamesFromFolderInJar(String folder) throws URISyntaxException, IOException {
        List<Path> result = getPathsFromResourceJAR(folder);
        return result.stream().map(f -> f.toString().replace(folder + "/", "")).collect(Collectors.toList());
    }

    private List<String> getFilesFromFolder(String folder) {

        List<String> filenames = new ArrayList<>();
        boolean iswindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (iswindows) folder = "/" + folder;
        try (
                InputStream in = getClass().getResourceAsStream(folder);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return filenames;
    }

    private List<String> getFileNamesFromFolder(String folder) {
        List<String> result = getFilesFromFolder(folder);
        return result.stream().map(f -> f.replace(folder + "/", "")).collect(Collectors.toList());
    }


    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    // Get all paths from a folder that inside the JAR file
    public List<Path> getPathsFromResourceJAR(String folder)
            throws URISyntaxException, IOException {

        List<Path> result;

        // get path of the current running JAR
        String jarPath = getClass().getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        System.out.println("JAR Path :" + jarPath);

        // file walks JAR
        URI uri = URI.create("jar:file:" + jarPath);
        try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            result = Files.walk(fs.getPath(folder))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        return result;

    }

    // print input stream
    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFilesToFolder(String fileDir) {
        File templateDirectory = Paths.get(fileDir).toFile();
        if (!templateDirectory.exists()) {
            templateDirectory.mkdir();
        }
        try {
            String protocol = this.getClass().getResource(this.getClass().getName() + ".class").getProtocol();
            if (Objects.equals(protocol, "jar")) {
                // run in jar
                copyFilesToOutside(fileDir);
            } else if (Objects.equals(protocol, "file")) {
                // run in ide
                copyFiles(fileDir);
            }
        } catch (Exception e) {
            //ignore
        }

    }

    private void copyFilesToOutside(String fileDir) throws URISyntaxException, IOException {

        getPathsFromResourceJAR(fileDir).forEach(s -> {
            try {
                System.out.println(s);
                long copied = Files.copy(getFileFromResourceAsStream(s.toString()), Paths.get(s.toString()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("copied bytes: " + copied);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void copyFiles(String fileDir) throws URISyntaxException, IOException {

        getFilesFromFolder(fileDir).forEach(s -> {
            try {
                System.out.println(s);
                long copied = Files.copy(getFileFromResourceAsStream(s.toString()), Paths.get(s.toString()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("copied bytes: " + copied);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}