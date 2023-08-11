package de.schemmea.ma.utils;

import javax.accessibility.AccessibleTableModelChange;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileResourcesUtils {

    public static void main(String[] args) throws IOException {

        FileResourcesUtils app = new FileResourcesUtils();

        // // Sample 3 - read all files from a resources folder (JAR version)
        // try {

        //     // get paths from src/main/resources/json
        //     List<Path> result = app.getPathsFromResourceJAR("json");
        //     for (Path path : result) {
        //         System.out.println("Path : " + path);

        //         String filePathInJAR = path.toString();
        //         // Windows will returns /json/file1.json, cut the first /
        //         // the correct path should be json/file1.json
        //         if (filePathInJAR.startsWith("/")) {
        //             filePathInJAR = filePathInJAR.substring(1, filePathInJAR.length());
        //         }

        //         System.out.println("filePathInJAR : " + filePathInJAR);

        //         // read a file from resource folder
        //         InputStream is = app.getFileFromResourceAsStream(filePathInJAR);
        //         printInputStream(is);
        //     }

        // } catch (URISyntaxException | IOException e) {
        //     e.printStackTrace();
        // }
    }

    public List<String> getResourceFiles(String folder) {
        try {
            if (FileResourcesUtils.runningFromJQF()) {
                return getFileNamesFromFolderFromJQF(folder);
            } else if (FileResourcesUtils.runningFromJar()) {
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

    public InputStream getResourceFileAsStream(String folder) {
        try {
            if (FileResourcesUtils.runningFromJQF()) {
                return Files.newInputStream(Paths.get(getExecutionContextName() + folder));
            } else if (FileResourcesUtils.runningFromJar()) {
                // run in jar
                return getClass().getResourceAsStream(folder);
            } else {
                // run in ide
                return getClass().getResourceAsStream(folder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getFileNamesFromFolderFromJQF(String folderName) {
        File folder = new File(getExecutionContextName() + folderName);
        File[] listOfFiles = folder.listFiles();

        return Arrays.stream(listOfFiles).map(f -> f.getName()).collect(Collectors.toList());
    }

    private static boolean runningFromJQF() {
        String jarname = getExecutionContextName();

        return jarname.contains("jqf") || jarname.contains("test-classes");
    }

    public static String getExecutionContextName() {
        try {
            return new File(FileResourcesUtils.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath())
                    .getAbsolutePath();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static boolean runningFromJar() {
        String jarname = getExecutionContextName();
        // System.out.println("ExecutionContext: " + jarname);
        return jarname.contains(".jar");
    }

    private List<String> getFileNamesFromFolderInJar(String folder) throws URISyntaxException, IOException {
        List<Path> result = getPathsFromResourceJAR(folder);
        return result.stream().map(f -> f.toString().replace(folder, "")).collect(Collectors.toList());
        //   return result.stream().map(f -> f.toFile().getName()).collect(Collectors.toList());
    }

    private List<String> getFilesFromFolder(String folder) {

        List<String> filenames = new ArrayList<>();
        boolean iswindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (iswindows && !folder.startsWith("C:") && !folder.startsWith("/")) folder = "/" + folder;
        try (
                InputStream in = getClass().getResourceAsStream(folder);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (Exception ex) {
            System.out.println("couldnt load " + folder);
            System.out.println(ex.getMessage());
        }

        return filenames;
    }

    public List<String> getFileNamesFromFolder(String folder) {
        List<String> result = getFilesFromFolder(folder);
        return result.stream().map(f -> f.replace(folder + "/", "")).collect(Collectors.toList());
    }


    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    public InputStream getFileFromResourceAsStream(String fileName) {
        if (fileName.startsWith("/")) fileName = fileName.substring(1);
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


    public void copyFilesToFolder(String filesInResources, String outsidePath) {
        File templateDirectory = Paths.get(outsidePath).toFile();
        if (!templateDirectory.exists()) {
            templateDirectory.mkdirs();
        }
        try {

            copyFiles(filesInResources, outsidePath);

        } catch (Exception e) {
            System.out.println("could not copy resources");
            System.out.println("    source: " + filesInResources);
            System.out.println("    target: " + outsidePath);

            System.out.println(e.getMessage());
            //ignore
        }

    }


    private void copyFiles(String fileDir, String outsidePath) throws IOException {
        if (!fileDir.endsWith("/")) fileDir += "/";
        List<String> files = getResourceFiles(fileDir);

        for (String s : files) {
            try {

                if (runningFromJar()) {
                    Files.copy(getFileFromResourceAsStream(fileDir + s), Paths.get(outsidePath, s), StandardCopyOption.REPLACE_EXISTING);
                } else if (runningFromJQF()) {
                    Files.copy(Paths.get(getExecutionContextName(), fileDir, s), Paths.get(outsidePath, s), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(getFileFromResourceAsStream(fileDir + s), Paths.get(outsidePath, s), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception ignored) {/** */}
        }
    }


}