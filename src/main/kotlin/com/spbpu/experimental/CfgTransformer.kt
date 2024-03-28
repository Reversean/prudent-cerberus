package com.spbpu.experimental

import com.spbpu.cfg.*
import org.jetbrains.research.libsl.nodes.*
import org.jetbrains.research.libsl.nodes.AssignOps.*

typealias CfgNodes = List<CfgNode>

object CfgTransformer {

    fun transformStatements(statements: List<Statement>): CfgNodes {
        val result = mutableListOf<CfgNode>()
        for (statement in statements) {
            val nodes = transformStatement(statement)
            result.lastOrNull()?.addNext(nodes.first())
            result.addAll(nodes)
        }
        return result
    }

    fun transformStatement(statement: Statement) = when (statement) {
        is Assignment -> transformAssignment(statement)
        is IfStatement -> transformIfStatement(statement)
        is ElseStatement -> transformElseStatement(statement)
        is ActionUsage -> transformActionUsage(statement)
        is ProcedureCall -> transformProcedureCall(statement)
        is FunctionUsage -> transformFunctionUsage(statement)
        is ExpressionStatement -> transformExpressionStatement(statement)
        is VariableDeclaration -> transformVariableDeclaration(statement)
    }

    fun transformAssignment(assignment: Assignment): CfgNodes {
        val lhv = assignment.left.lastChild
        val variable = when (lhv) {
            is ArrayAccess -> TODO()
            is AutomatonProcedureCall -> TODO()
            is AutomatonVariableInvoke -> TODO()
            is ThisAccess -> TODO()
            is VariableAccess -> lhv.variable
        }
        return AssignmentCfgNode(variable, assignment.assignedExpression).wrap()
    }

    fun transformIfStatement(ifStatement: IfStatement): CfgNodes {
        val trueBranchNodes = transformStatements(ifStatement.ifStatements)
        val falseBranchNodes = ifStatement.elseStatements
            ?.let { transformElseStatement(it) }
        val terminalNode = StubCfgNode()
        val branchNode = BranchCfgNode(
            ifStatement.value,
            trueBranchNodes.first(),
            falseBranchNodes?.first() ?: terminalNode
        )
        trueBranchNodes.lastOrNull()?.addNext(terminalNode)
        falseBranchNodes?.lastOrNull()?.addNext(terminalNode)

        val result = mutableListOf<CfgNode>()
        result.add(branchNode)
        result.addAll(trueBranchNodes)
        falseBranchNodes?.let { result.addAll(it) }
        result.add(terminalNode)
        return result
    }

    fun transformElseStatement(elseStatement: ElseStatement) =
        transformStatements(elseStatement.statements)

    fun transformActionUsage(actionUsage: ActionUsage) =
        ActionUsageCfgNode(actionUsage).wrap()

    fun transformProcedureCall(procedureCall: ProcedureCall) =
        ProcedureCallCfgNode(procedureCall).wrap()

    fun transformFunctionUsage(functionUsage: FunctionUsage) =
        FunctionUsageCfgNode(functionUsage).wrap()

    fun transformExpressionStatement(expressionStatement: ExpressionStatement) =
        ExpressionCfgNode(expressionStatement.expression).wrap()

    fun transformVariableDeclaration(variableDeclaration: VariableDeclaration) =
        VariableDeclarationCfgNode(variableDeclaration.variable).wrap()

    private val Assignment.assignedExpression
        get() = when (op) {
            ASSIGN -> value
            COMP_ADD -> createAssignedExpression(ArithmeticBinaryOps.ADD)
            COMP_SUB -> createAssignedExpression(ArithmeticBinaryOps.SUB)
            COMP_MUL -> createAssignedExpression(ArithmeticBinaryOps.MUL)
            COMP_DIV -> createAssignedExpression(ArithmeticBinaryOps.DIV)
            COMP_MOD -> createAssignedExpression(ArithmeticBinaryOps.MOD)
            COMP_AND -> createAssignedExpression(ArithmeticBinaryOps.AND)
            COMP_OR -> createAssignedExpression(ArithmeticBinaryOps.BIT_OR)
            COMP_XOR -> createAssignedExpression(ArithmeticBinaryOps.XOR)
            COMP_R_SHIFT -> createAssignedExpression(ArithmeticBinaryOps.R_SHIFT)
            COMP_UN_R_SHIFT -> createAssignedExpression(ArithmeticBinaryOps.UNSIGNED_R_SHIFT)
            COMP_L_SHIFT -> createAssignedExpression(ArithmeticBinaryOps.L_SHIFT)
        }

    private fun Assignment.createAssignedExpression(op: ArithmeticBinaryOps) =
        BinaryOpExpression(left, value, op, entityPosition)

    private fun CfgNode.wrap(): CfgNodes = listOf(this)
}
