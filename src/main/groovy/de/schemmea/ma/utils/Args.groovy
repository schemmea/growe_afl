package de.schemmea.ma.utils

import com.beust.jcommander.Parameter

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

    @Parameter(names = ["--duration", "-d"], description = "How long should zest run in maximum")
    private int durationInSeconds = 360;

    @Parameter(names = ["--iteration", "-i"], description = "How many tests should zest perform")
    private int iteration = 5;

}
