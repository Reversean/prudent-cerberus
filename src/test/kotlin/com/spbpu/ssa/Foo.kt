package com.spbpu.ssa

import com.spbpu.ssa.value.IntConst

//val foo = automaton("FooAutomaton") {
//    variable("a", "int")
//    variable("b", "int", IntConst(0))
//    variable("c", "int", IntConst(0))
//
//    constructor {
//        arg("a", "int")
//        block {
//            assignment("this.a", variable("a"))
//        }
//    }
//
//    function("setB") {
//        arg("value", "int")
//        block {
//            assignment(`this`("b"), arg("value"))
//        }
//    }
//
//    function("incC") {
//        block {
//            val temp = sum(`this`("c"), IntConst(1))
//            assign(`this`("c"), temp)
//        }
//    }
//}
