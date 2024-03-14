package com.spbpu.ssa

class Automaton(
    name: String,
    val constructors: List<Function>,
    val functions: List<Function>,

): Node(name) {
}