package de.schemmea.ma.guidance;

import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import edu.berkeley.cs.jqf.fuzz.ei.*;
import edu.berkeley.cs.jqf.fuzz.guidance.GuidanceException;
import edu.berkeley.cs.jqf.fuzz.guidance.Result;
import edu.berkeley.cs.jqf.fuzz.util.FastNonCollidingCoverage;
import javassist.bytecode.stackmap.BasicBlock;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileAwareZestGuidance extends ZestGuidance {

    private final FileAwareResultListener listener;
    private Object[] lastArgs;

    public FileAwareZestGuidance(String testName, Duration duration, Long trials, File outputDirectory, Random rnd, FileAwareResultListener listener) throws IOException {
        super(testName, duration, trials, outputDirectory, rnd);
        this.listener = listener;

    }

    @Override
    public void handleResult(Result result, Throwable error) throws GuidanceException {
        super.handleResult(result, error);
        conditionallySynchronize(multiThreaded, () -> {
            if (listener != null) {
                try {
                    this.listener.handleResultForGeneratedArgs(lastArgs, result, error);
                } catch (IOException e) {
                    throw new GuidanceException(e);
                }
            }
        });
    }

    @Override
    public void observeGeneratedArgs(Object[] args) {
        this.lastArgs = args;
    }

    @FunctionalInterface
    public interface FileAwareResultListener {
        void handleResultForGeneratedArgs(Object[] generated, Result result, Throwable error) throws IOException;
    }


    @Override
    protected void displayStats(boolean force) {
        Date now = new Date();
        long intervalMilliseconds = now.getTime() - lastRefreshTime.getTime();
        intervalMilliseconds = Math.max(1, intervalMilliseconds);
        if (intervalMilliseconds < STATS_REFRESH_TIME_PERIOD && !force) {
            return;
        }
        long interlvalTrials = numTrials - lastNumTrials;
        long intervalExecsPerSec = interlvalTrials * 1000L;
        double intervalExecsPerSecDouble = interlvalTrials * 1000.0;
        if (intervalMilliseconds != 0) {
            intervalExecsPerSec = interlvalTrials * 1000L / intervalMilliseconds;
            intervalExecsPerSecDouble = interlvalTrials * 1000.0 / intervalMilliseconds;
        }
        lastRefreshTime = now;
        lastNumTrials = numTrials;
        long elapsedMilliseconds = now.getTime() - startTime.getTime();
        elapsedMilliseconds = Math.max(1, elapsedMilliseconds);
        long execsPerSec = numTrials * 1000L / elapsedMilliseconds;

        String currentParentInputDesc;
        if (seedInputs.size() > 0 || savedInputs.isEmpty()) {
            currentParentInputDesc = "<seed>";
        } else {
            Input currentParentInput = savedInputs.get(currentParentInputIdx);
            currentParentInputDesc = currentParentInputIdx + " ";
            currentParentInputDesc += currentParentInput.isFavored() ? "(favored)" : "(not favored)";
            currentParentInputDesc += " {" + numChildrenGeneratedForCurrentParentInput +
                    "/" + getTargetChildrenForParent(currentParentInput) + " mutations}";
        }

        int nonZeroCount = totalCoverage.getNonZeroCount();
        double nonZeroFraction = nonZeroCount * 100.0 / totalCoverage.size();
        int nonZeroValidCount = validCoverage.getNonZeroCount();
        double nonZeroValidFraction = nonZeroValidCount * 100.0 / validCoverage.size();

        System.out.println();
        if (LIBFUZZER_COMPAT_OUTPUT) {
            System.out.printf("#%,d\tNEW\tcov: %,d exec/s: %,d L: %,d\n", numTrials, nonZeroValidCount, intervalExecsPerSec, currentInput.size());
        } else if (!QUIET_MODE) {
            System.out.printf("\033[2J");
            System.out.printf("\033[H");
            System.out.printf(this.getTitle() + "\n");
            if (this.testName != null) {
                System.out.printf("Test name:            %s\n", this.testName);
            }


            String instrumentationType = "Janala";
            if (this.runCoverage instanceof FastNonCollidingCoverage) {
                instrumentationType = "Fast";
            }
            System.out.printf("Instrumentation:      %s\n", instrumentationType);
            System.out.printf("Results directory:    %s\n", this.outputDirectory.getAbsolutePath());
            System.out.printf("Elapsed time:         %s (%s)\n", millisToDuration(elapsedMilliseconds),
                    maxDurationMillis == Long.MAX_VALUE ? "no time limit" : ("max " + millisToDuration(maxDurationMillis)));
            System.out.printf("Number of executions: %,d (%s)\n", numTrials,
                    maxTrials == Long.MAX_VALUE ? "no trial limit" : ("max " + maxTrials));
            System.out.printf("Valid inputs:         %,d (%.2f%%)\n", numValid, numValid * 100.0 / numTrials);
            System.out.printf("Cycles completed:     %d\n", cyclesCompleted);
            System.out.printf("Unique failures:      %,d\n", uniqueFailures.size());
            System.out.printf("Queue size:           %,d (%,d favored last cycle)\n", savedInputs.size(), numFavoredLastCycle);
            System.out.printf("Current parent input: %s\n", currentParentInputDesc);
            System.out.printf("Execution speed:      %,d/sec now | %,d/sec overall\n", intervalExecsPerSec, execsPerSec);
            System.out.printf("Total coverage:       %,d branches (%.2f%% of map)\n", nonZeroCount, nonZeroFraction);
            System.out.printf("Valid coverage:       %,d branches (%.2f%% of map)\n", nonZeroValidCount, nonZeroValidFraction);
        }

        String plotData = String.format("%d; %d; %d; %d; %d; %d; %.2f, %d; %d; %d; %.2f, %d; %d; %.2f, %d; %d",
                TimeUnit.MILLISECONDS.toSeconds(now.getTime()), cyclesCompleted, currentParentInputIdx,
                numSavedInputs, 0, 0, nonZeroFraction, uniqueFailures.size(), 0, 0, intervalExecsPerSecDouble,
                numValid, numTrials - numValid, nonZeroValidFraction, nonZeroCount, nonZeroValidCount);
        appendLineToFile(statsFile, plotData);
    }

}

