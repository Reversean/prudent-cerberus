package com.spbpu.cfg

import org.jetbrains.research.libsl.nodes.*
import org.jetbrains.research.libsl.nodes.references.VariableReference

abstract class CfgNode {
    val predecessors = mutableListOf<CfgNode>()
    val successors = mutableListOf<CfgNode>()

    fun addNext(successor: CfgNode) {
        addSuccessor(successor)
        successor.addPredecessor(this)
    }

    private fun addPredecessor(predecessor: CfgNode) {
        predecessors.add(predecessor)
    }

    private fun addSuccessor(successor: CfgNode) {
        successors.add(successor)
    }
}

class StubCfgNode: CfgNode()

class AssignmentCfgNode(
    val variable: VariableReference,
    val value: Expression
): CfgNode()

class BranchCfgNode(
    val condition: Expression,
    val trueSuccessor: CfgNode,
    val falseSuccessor: CfgNode
): CfgNode() {
    init {
        addNext(trueSuccessor)
        addNext(falseSuccessor)
    }
}

class ActionUsageCfgNode(
    val actionUsage: ActionUsage
): CfgNode()

class FunctionUsageCfgNode(
    val functionUsage: FunctionUsage
): CfgNode()

class ProcedureCallCfgNode(
    val call: ProcedureCall
): CfgNode()

class ExpressionCfgNode(
    val expression: Expression
): CfgNode()

class VariableDeclarationCfgNode(
    val variable: VariableWithInitialValue
): CfgNode()
