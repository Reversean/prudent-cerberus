package com.spbpu.experimental

import com.spbpu.cfg.AutomatonCfg
import org.jetbrains.research.libsl.LibSL

val libsl = LibSL("src/main/resources/libsl")
val library = libsl.loadFromFileName("com/spbpu/experimental/Example.lsl")

val automatons = library.automata.map { AutomatonCfg(it) }

