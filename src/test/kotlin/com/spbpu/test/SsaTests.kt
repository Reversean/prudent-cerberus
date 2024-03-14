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
        val cm = ClassManager(KfgConfig(Flags.readAll, failOnError = true))
        cm.initialize(jar)
        for (klass in cm.concreteClasses) {
            for (method in klass.allMethods) {
                method.print()
            }
        }
    }
})
