package com.spbpu.cfg

import com.spbpu.experimental.CfgTransformer
import org.jetbrains.research.libsl.nodes.Function

class FunctionCfg(
    val function: Function
) {

    val body= CfgTransformer.transformStatements(function.statements)
}
