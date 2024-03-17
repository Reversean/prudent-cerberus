package com.spbpu.test

import io.kotest.core.spec.style.FunSpec
import org.jetbrains.research.libsl.LibSL

class LibslTests : FunSpec({
    test("AtomicBoolean") {
        val libsl = LibSL("test-data/libsl")
        val atomicBoolean = libsl.loadFromFileName("java/util/concurrent/atomic/AtomicBoolean.lsl")
        val atomicBooleanMain = libsl.loadFromFileName("java/util/concurrent/atomic/AtomicBoolean.main.lsl")

        val atomicBooleanAutomaton = atomicBooleanMain.automata[0]
        val toStringFun = atomicBooleanAutomaton.functions.find { it.name == "toString" }

        println(atomicBoolean)
        println(atomicBooleanMain)
        println(atomicBooleanAutomaton)
        println(toStringFun)
    }

    test("Foo") {
        val libsl = LibSL("test-data/libsl")
        val library = libsl.loadFromFileName("com/spbpu/example/Foo.lsl")
        val automaton = library.automata.first()

        println(automaton)
    }
})
