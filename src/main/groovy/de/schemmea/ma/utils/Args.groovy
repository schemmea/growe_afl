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

    int getIteration() {
        return iteration
    }

    void setIteration(int iteration) {
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

    @Parameter(names = ["--duration", "-d"], description = "How long should we run in maximum")
    private int durationInSeconds = 360;


    @Parameter(names = ["--iteration", "-i"], description = "How many tests should we perform")
    private int iteration = 5;


    @Parameter(names = ["--keepTestFile", "-k"], description = "Should keep testfile after run")
    private boolean keepTestFile = false;


    @Parameter(names = ["--useBaseline", "-b"], description = "Should we run experiments with baseline generation?")
    private boolean useBaseline = false;


    @Parameter(names = ["--guidance", "-g"], description = "Use zest guidance or ei guidance")
    private String guidance = "zest";


}
