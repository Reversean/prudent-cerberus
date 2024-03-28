package com.spbpu.cfg

import org.jetbrains.research.libsl.nodes.Automaton

class AutomatonCfg(
    val automaton: Automaton
) {
    val internalVariables = automaton.internalVariables
    val constructorVariables = automaton.constructorVariables

    val secondaryConstructors = automaton.constructors
        .map { FunctionCfg(it) }
    val functions = automaton.functions
        .map { FunctionCfg(it) }
    val procedures = automaton.procDeclarations
        .map { FunctionCfg(it) }

}