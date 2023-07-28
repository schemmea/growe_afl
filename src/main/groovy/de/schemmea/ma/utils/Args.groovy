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

    @Parameter(names = ["--duration", "-d"], description = "How long should zest run in maximum")
    private int durationInSeconds = 360;

    @Parameter(names = ["--iteration", "-i"], description = "How many tests should zest perform")
    private int iteration = 5;


    @Parameter(names = ["--keepTestFile", "-k"], description = "Should keep testfile after run")
    private boolean keepTestFile = false;
}
