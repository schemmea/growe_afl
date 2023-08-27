package de.schemmea.ma;

class TestExecutor {

    public static void main(String... args) {

        /**dummy main class for building jar**/

        String testname = "testAFL";
        Class testclass = NfAFLTest.class;


        System.out.println("Testing" + testclass.getName() + "#" + testname + " with AFL");
    }

}

