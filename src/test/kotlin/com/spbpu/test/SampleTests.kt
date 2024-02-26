package com.spbpu.test

import com.spbpu.LibSLObjectDecoder
import com.spbpu.jacodb.JacoDBContainer
import com.spbpu.util.ensureJslSpecRepo
import io.kotest.core.spec.style.FunSpec
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
        val type = TypeNameImpl("Integer").toJcClassOrInterface(dbContainer.cp)
        println(type)
//        val data = JcTestStateResolver.TestObjectData()
//        decoder.initializeInstance(
//
//        )
    }

})
