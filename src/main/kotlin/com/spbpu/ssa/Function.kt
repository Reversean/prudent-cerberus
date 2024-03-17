package com.spbpu.ssa

open class Function(
    name: String
) : Node(name) {
    val args: LinkedHashSet<FunctionArgument> = linkedSetOf()
    val body: LinkedHashSet<Block> = linkedSetOf()


}

class Constructor(
    name: String
) : Function(name) {
}

fun Automaton.constructor(
    build: Constructor.() -> Unit
): Constructor {
    val ctor = Constructor("<init>").apply(build)
    addFunction(ctor)
    return ctor
}

fun Automaton.function(
    name: String,
    build: Function.() -> Unit
): Function {
    val ctor = Function(name).apply(build)
    addFunction(ctor)
    return ctor
}
