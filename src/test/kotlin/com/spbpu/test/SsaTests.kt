package com.spbpu.test

import io.kotest.assertions.print.print
import io.kotest.core.spec.style.FunSpec
import org.vorpal.research.kfg.ClassManager
import org.vorpal.research.kfg.KfgConfig
import org.vorpal.research.kfg.Package
import org.vorpal.research.kfg.container.Container
import org.vorpal.research.kfg.container.DirectoryContainer
import org.vorpal.research.kfg.container.JarContainer
import org.vorpal.research.kfg.util.Flags
import kotlin.io.path.Path

val usvmCoreJarPath = "test-data/jar/usvm-core.jar"
val stdlibDirPath = "test-data/class/java.base"

fun stdlibClassManager(packagePath: String): ClassManager {
//    val `package` = Package(packagePath)
    val path = Path(stdlibDirPath).resolve(packagePath)
    val container = DirectoryContainer(path, Package.emptyPackage)
    return classManager(container)
}

fun usvmCoreClassManager(packagePath: String): ClassManager {
    val `package` = Package(packagePath)
    val container = JarContainer(usvmCoreJarPath, `package`)
    return classManager(container)
}

fun classManager(container: Container): ClassManager {
    val config = KfgConfig(Flags.readAll, failOnError = true)
    val cm = ClassManager(config)
    cm.initialize(container)
    return cm
}

class SsaTests : FunSpec({

    test("utils") {
        val cm = usvmCoreClassManager("org/usvm/utils")
        val `class` = cm.concreteClasses
            .first { it.name == "StateUtilsKt" }
        val methodSimple = `class`.methods
            .first { it.name == "isSat" }
        val methodComplex = `class`.methods
            .first { it.name == "applySoftConstraints" }
        `class`.print()
        methodSimple.print()
        methodComplex.print()
    }

    test("Field method call") {
        val cm = usvmCoreClassManager("org/usvm/statistics")
        val `class` = cm.concreteClasses
            .first { it.name == "TimeStatistics" }
        val method = `class`.methods
            .first { it.name == "onMachineStopped" }
        `class`.print()
        method.print()
    }

    test("Field assignment") {
        val cm = usvmCoreClassManager("org/usvm/stopstrategies")
        val `class` = cm.concreteClasses
            .first { it.name == "StepsFromLastCoveredStopStrategy" }
        val method = `class`.methods
            .first { it.name == "shouldStop" }
        `class`.print()
        method.print()
    }

    test("Private method call") {
        val cm = usvmCoreClassManager("org/usvm/memory")
        val `class` = cm.concreteClasses
            .first { it.name == "URegistersStack" }
        val publicMethod = `class`.methods
            .first { it.name == "mergeWith" }
        val privateMethod = `class`.methods
            .first { it.name == "validate" }
        `class`.print()
        publicMethod.print()
        privateMethod.print()
    }

    test("AtomicBoolean") {
        val cm = stdlibClassManager("java/util/concurrent/atomic")
        val `class` = cm.concreteClasses
            .first { it.name == "AtomicBoolean" }
        val method = `class`.methods
            .first { it.name == "toString" }
        `class`.print()
        method.print()
    }

    test("Boolean") {
        val cm = stdlibClassManager("java/lang")
        val `class` = cm.concreteClasses
            .first { it.name == "Boolean" }
        val method = `class`.methods
            .first { it.name == "toString" }
        `class`.print()
        method.print()
    }
})
