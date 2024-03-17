package com.spbpu.ssa.instruction

import com.spbpu.ssa.Block
import com.spbpu.ssa.Variable
import com.spbpu.ssa.VersionedVariable
import com.spbpu.ssa.value.Value

class Assignment(
    val lhv: VersionedVariable,
    val rhv: Value
) {
}

//fun Block.assignment(lhv: Variable, rhv: Value): Assignment {
//    val versioned = VersionedVariable(lhv, )
//    return Assignment()
//}
