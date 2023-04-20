package de.schemmea.ma


import groovy.test.GroovyTestCase

class HelloTest extends GroovyTestCase {
    void 'test Hello should return "Hello, World!"' () {
        assert new Hello().world == "Hello, World!"
    }
}
