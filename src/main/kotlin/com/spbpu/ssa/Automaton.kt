package com.spbpu.ssa

class Automaton(
    name: String,
    val constructors: List<Function> = emptyList(),
    val functions: List<Function> = emptyList()
): Node(name) {
}