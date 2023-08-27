package de.schemmea.ma


class TestExecutor {

    static void main(String... args) {

        String testname = "testAFL"
        Class testclass = NfAFLTest.class

        println "Testing $testclass.name#$testname AFL - calling groovy "
    }

}

