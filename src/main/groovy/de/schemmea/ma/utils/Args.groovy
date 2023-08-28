package de.schemmea.ma.utils

import com.beust.jcommander.Parameter
import org.apache.tools.ant.taskdefs.XSLTProcess

class Args {

    int getDurationInSeconds() {
        return durationInSeconds
    }

    void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds
    }

    Long getIteration() {
        return iteration > 0 ? iteration : null
    }

    void setIteration(Long iteration) {
        this.iteration = iteration
    }

    boolean getKeepTestFile() {
        return keepTestFile
    }

    void setKeepTestFile(boolean keepTestFile) {
        this.keepTestFile = keepTestFile
    }

    String getGuidance() {
        return guidance
    }

    void setGuidance(String guidance) {
        this.guidance = guidance
    }

    boolean getUseBaseline() {
        return useBaseline
    }

    void setUseBaseline(boolean useBaseline) {
        this.useBaseline = useBaseline
    }

    String getReproDir() {
        return reproDir
    }

    void setReproDir(String reproDir) {
        this.reproDir = reproDir
    }

    boolean getDebug() {
        return debug
    }

    void setDebug(boolean debug) {
        this.debug = debug
    }

    @Parameter(names = ["--duration", "-d"], description = "How long should we run in maximum")
    private int durationInSeconds = 3600;


    @Parameter(names = ["--iteration", "-i"], description = "How many tests should we perform")
    private Long iteration = 0;


    @Parameter(names = ["--keepTestFile", "-k"], description = "Should keep testfile after run")
    private boolean keepTestFile = false;


    @Parameter(names = ["--useBaseline", "-b"], description = "Should we run experiments with baseline generation?")
    private boolean useBaseline = false;


    @Parameter(names = ["--guidance", "-g"], description = "Use zest, ei, repro guidance")
    private String guidance = "zest";


    @Parameter(names = ["--reproDir", "-r"], description = "path to jqfs errorDir/corpus")
    private String reproDir = "";


    @Parameter(names = ["-debug"], description = "debug")
    private boolean debug = false;


}