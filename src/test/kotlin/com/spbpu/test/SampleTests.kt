package com.spbpu.test

import com.spbpu.CustomObjectData
import com.spbpu.LibSLObjectDecoder
import com.spbpu.jacodb.JacoDBContainer
import com.spbpu.util.ensureJslSpecRepo
import io.kotest.core.spec.style.FunSpec
import org.jacodb.api.ext.findClass
import org.usvm.UMachineOptions
import org.usvm.api.util.JcClassLoader
import org.usvm.api.util.JcTestInterpreterDecoderApi
import org.usvm.machine.JcMachine
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

class Foo(val a: Int) {
    private var b = 0
    fun setB(value: Int) {
        b = value
    }
}
