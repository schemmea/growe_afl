package de.schemmea.ma

import de.schemmea.ma.utils.Configuration
import de.schemmea.ma.utils.FileResourcesUtils


class Hello {
    def getWorld() {
        "Hello, World!"
    }

    static main(args) {
     // new  FooBar().launchOneScript();

        new FileResourcesUtils().copyFilesToFolder(Configuration.TEMPLATE_SOURCE_PATH, Configuration.OUTPUT_TEMPLATE_PATH);
    }



}