package de.schemmea.ma.guidance

import edu.berkeley.cs.jqf.fuzz.ei.*
import edu.berkeley.cs.jqf.fuzz.guidance.GuidanceException
import edu.berkeley.cs.jqf.fuzz.guidance.Result

import java.time.Duration

class FileAwareZestGuidance extends ZestGuidance {

    private final FileAwareResultListener listener;
    private Object[] lastArgs;

    FileAwareZestGuidance(String testName, Duration duration, Long trials, File outputDirectory, Random rnd, FileAwareResultListener listener) throws IOException {
        super(testName, duration, trials, outputDirectory, rnd);
        this.listener = listener;
    }

    @Override
    public void handleResult(Result result, Throwable error) throws GuidanceException {
        super.handleResult(result, error);
        if (listener != null) {
            this.listener.handleResultForGeneratedArgs(lastArgs, result, error);
        }
    }

    @Override
    public void observeGeneratedArgs(Object[] args) {
        this.lastArgs = args;
    }

    @FunctionalInterface
    public interface FileAwareResultListener {
        void handleResultForGeneratedArgs(Object[] generated, Result result, Throwable error) throws IOException;
    }


}

