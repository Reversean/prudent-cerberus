package com.spbpu.jacodb

import kotlinx.coroutines.runBlocking
import org.jacodb.api.JcClasspath
import org.jacodb.api.JcDatabase
import org.jacodb.approximation.Approximations
import org.jacodb.impl.features.InMemoryHierarchy
import org.jacodb.impl.jacodb
import org.usvm.util.classpathWithApproximations

const val bbb = """
    D:\Poly\usvm\usvm-jvm\build\classes\java\samples;
    D:\Poly\usvm\usvm-jvm\build\classes\kotlin\samples;
    D:\Poly\usvm\usvm-jvm\build\resources\samples;
    D:\Poly\usvm\usvm-jvm\build\classes\java\usvm-api;
    D:\Poly\usvm\usvm-jvm\build\classes\kotlin\usvm-api;
    D:\Poly\usvm\usvm-jvm\build\resources\usvm-api;
    D:\Poly\usvm\usvm-jvm\build\classes\java\sample-approximations;
    D:\Poly\usvm\usvm-jvm\build\classes\kotlin\sample-approximations;
    D:\Poly\usvm\usvm-jvm\build\resources\sample-approximations;
    C:\Users\Odahvii\.gradle\caches\modules-2\files-2.1\com.github.UnitTestBot.java-stdlib-approximations\tests\d9d6f30985\b4ac0ce9d4084541030a517d257759778a815e5d\tests-d9d6f30985.jar
"""

class JacoDBContainer {

    var db: JcDatabase
    var cp: JcClasspath

    init {
        runBlocking {
            db = jacodb {
                useProcessJavaRuntime()
                installFeatures(InMemoryHierarchy)
                installFeatures(Approximations)
            }
            cp = db.classpath(listOf())
            db.awaitBackgroundJobs()
        }
    }
}