package com.spbpu.test

import com.spbpu.LibSLObjectDecoder
import com.spbpu.jacodb.JacoDBContainer
import com.spbpu.util.ensureJslSpecRepo
import io.kotest.core.spec.style.FunSpec
import org.jacodb.api.ext.findClass
import org.jacodb.impl.jacodb
import org.jacodb.impl.types.TypeNameImpl
import org.usvm.api.util.JcTestStateResolver
import org.usvm.instrumentation.util.toJcClassOrInterface

const val specRepoPath = "src/test/resources"
const val specPath = "src/test/resources/sample.lsl"

class SampleTests: FunSpec( {

    val jslSpecRepo = ensureJslSpecRepo()
    val dbContainer = JacoDBContainer()

    test("Sample") {
        val decoder = LibSLObjectDecoder(jslSpecRepo.path)

        val origin = Foo(2).apply { b = 42 }
        val type = dbContainer.cp.findClass(origin::class.qualifiedName!!)
        println(type)
//        val data = JcTestStateResolver.TestObjectData()

//        decoder.initializeInstance(
//
//        )
    }

})

class Foo(val a: Int) {
    var b = 0
}
