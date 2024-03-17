package com.spbpu.ssa

class Automaton(
    name: String
): Node(name) {
    private val variables: LinkedHashSet<Variable> = linkedSetOf()
    private val functions: LinkedHashSet<Function> = linkedSetOf()
    private val constructors: LinkedHashSet<Function> = linkedSetOf()

    fun addVariable(variable: Variable) {
        variables.add(variable)
    }
    fun addFunction(function: Function) {
        functions.add(function)
    }
    fun addConstructor(constructor: Constructor) {
        constructors.add(constructor)
    }
}

fun automaton(name: String, build: Automaton.() -> Unit): Automaton {
    return Automaton(name).apply(build)
}
