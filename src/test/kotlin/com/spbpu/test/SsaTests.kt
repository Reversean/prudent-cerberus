package com.spbpu.test

import io.kotest.core.spec.style.FunSpec
import java.nio.file.Paths
import org.vorpal.research.kfg.ClassManager
import org.vorpal.research.kfg.KfgConfig
import org.vorpal.research.kfg.Package
import org.vorpal.research.kfg.container.Container
import org.vorpal.research.kfg.container.JarContainer
import org.vorpal.research.kfg.util.Flags
import kotlin.io.path.Path

class SsaTests : FunSpec({
    test("kfg") {
        val path = Path("test-data/jar/usvm-core.jar")
        val `package` = Package("org/usvm/utils")
        val jar = JarContainer(path, `package`)
        val target = Paths.get("test-data/jar/updated")
        // create ClassManager and initialize it with the jar
        val cm = ClassManager(KfgConfig(Flags.readAll, failOnError = true))
        cm.initialize(jar)
        // iterate over all found classes
        for (klass in cm.concreteClasses) {
            for (method in klass.allMethods) {
                // view each method as graph
                method.view("test-data/kfg/dot", "test-data/kfg/browser")
            }
        }
        // create copy of the original jar with the updated
        // classes in directory `target`
        jar.update(cm, target)
    }
})
