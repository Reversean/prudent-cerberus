package com.spbpu.jacodb

import kotlinx.coroutines.runBlocking
import org.jacodb.api.JcClasspath
import org.jacodb.api.JcDatabase
import org.jacodb.approximation.Approximations
import org.jacodb.impl.features.InMemoryHierarchy
import org.jacodb.impl.jacodb
import org.usvm.util.classpathWithApproximations
import java.io.File

val additionalClasspath = listOf<String>(
    "C:\\Work\\prudent-cerberus\\build\\classes\\kotlin\\test"
).map(::File)

class JacoDBContainer {

    var db: JcDatabase
    var cp: JcClasspath

    init {
        runBlocking {
            db = jacodb {
                loadByteCode(additionalClasspath)
                useProcessJavaRuntime()
                installFeatures(InMemoryHierarchy)
                installFeatures(Approximations)
            }
            cp = db.classpath(additionalClasspath)
            db.awaitBackgroundJobs()
        }
    }
}