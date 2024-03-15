package com.spbpu.ssa

//val block0 =

val blocks = listOf(
    Block("toString%block", 0),
    Block("toString%if.then", 0),
    Block("toString%if.else", 0),
    Block("toString%return", 0),
).apply {
    get(0).addSuccessor(get(1))
    get(0).addSuccessor(get(2))

    get(1).addPredecessor(get(0))
    get(1).addSuccessor(get(3))

    get(2).addPredecessor(get(0))
    get(2).addSuccessor(get(3))

    get(3).addPredecessor(get(1))
    get(3).addPredecessor(get(2))
}


val body = FunctionBody(
    block = blocks
)

val functions = listOf(
    Function("toString", body)
)

val automaton = Automaton(
    name = "AtomicBoolean",
    functions = functions
)
