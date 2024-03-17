package com.spbpu.test

import com.spbpu.CustomObjectData
import com.spbpu.example.Foo
import com.spbpu.LibSLObjectDecoder
import com.spbpu.jacodb.JacoDBContainer
import io.kotest.core.spec.style.FunSpec
import org.jacodb.api.ext.findClass
import java.io.File

class SampleTests: FunSpec({

    val jslSpecRepo = File("test-data/libsl")
    val dbContainer = JacoDBContainer()

    test("Sample") {
        val decoder = LibSLObjectDecoder(jslSpecRepo.path)

        val obj = Foo(2).apply { setB(42) }
        val type = dbContainer.cp.findClass(obj::class.qualifiedName!!)
        val data = CustomObjectData(obj)
        decoder.createInstance(type, data)
    }

})
