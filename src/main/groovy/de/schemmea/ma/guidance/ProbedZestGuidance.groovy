package de.schemmea.ma.guidance

import edu.berkeley.cs.jqf.fuzz.ei.*

import java.time.Duration

class ProbedZestGuidance extends ZestGuidance {
    List<Integer> inputHashes = new ArrayList<>();

    ProbedZestGuidance(String testName, Duration duration, Long trials, File outputDirectory, Random rnd) throws IOException {
        super(testName, duration, trials, outputDirectory, rnd);
    }

    @Override
    public void observeGeneratedArgs(Object[] args) {
        this.inputHashes.add(Arrays.hashCode(args));
    }

    int hashInputHashes() {
        return inputHashes.hashCode();
    }

    int corpusCount() {
        return savedInputs.size();
    }

    int hashTotalCoverage() {
        return totalCoverage.hashCode();
    }

    int hashValidCoverage() {
        return validCoverage.hashCode();
    }
}

