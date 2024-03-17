package com.spbpu.ssa

import com.spbpu.ssa.instruction.Instruction

open class Block {
    val predecessors: LinkedHashSet<Block> = linkedSetOf()
    val successors: LinkedHashSet<Block> = linkedSetOf()
    val instructions: LinkedHashSet<Instruction> = linkedSetOf()

    fun addPredecessor(block: Block) = predecessors.add(block)
    fun addSuccessor(block: Block) = successors.add(block)
    fun addInstruction(instruction: Instruction) = instructions.add(instruction)

}

fun Function.block(
    build: Block.() -> Unit
): Block {
    return Block().apply(build)
}
