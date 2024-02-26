package com.spbpu.test

import com.spbpu.util.ensureJslSpecRepo
import decoders.java.lang.Integer_Decoder
import decoders.java.util.ArrayList_Decoder
import generated.java.lang.Integer
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

val integerTestData = listOf(null, 0, 1, -1, 42, Integer.MIN_VALUE, Integer.MAX_VALUE)

val arrayListTestData = mapOf(
    "Empty list" to arrayListOf(),
    "List[4] = 42" to arrayListOf<Int>().apply {
        add(4, 42)
    }
)

class DecodersTests : FunSpec({
    val jslSpecRepo = ensureJslSpecRepo()

    context("Integer") {
        xcontext("Approximation") {
            val decoder = Integer_Decoder()
            withData(integerTestData) {

            }
        }
        context("LibSL") {
            withData(integerTestData) {

            }
        }
    }

    context("ArrayList") {
        xcontext("Approximation") {
            val decoder = ArrayList_Decoder()
            withData(integerTestData) {

            }
        }
        context("LibSL") {
            withData(integerTestData) {

            }
        }
    }
})
