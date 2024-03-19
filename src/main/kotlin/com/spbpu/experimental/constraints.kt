package com.spbpu.experimental

import org.jetbrains.research.libsl.context.FunctionContext
import org.jetbrains.research.libsl.context.LslGlobalContext
import org.jetbrains.research.libsl.nodes.Function
import org.jetbrains.research.libsl.nodes.FunctionArgument
import org.jetbrains.research.libsl.nodes.FunctionKind
import org.jetbrains.research.libsl.nodes.references.TypeReference
import org.jetbrains.research.libsl.utils.EntityPosition
import org.jetbrains.research.libsl.utils.PositionInfo

interface Value

class IntConst(val value: Int) : Value
class StringConst(val value: String) : Value
class ObjectValue(val value: List<Constraint>) : Value
// TODO: make other default types from libsl

abstract class Constraint(val name: String)

class VariableValueConstraint(
    name: String,
    val value: Value?
) : Constraint(name)

class FunctionResultConstraint(
    val function: Function,
    val args: Map<String, Value>,
    val result: Value?
) : Constraint(function.fullName)

val stubContext = LslGlobalContext("")
val stubPosition = EntityPosition("", PositionInfo(0, 0), PositionInfo(0, 0))
val stubIntTypeReference = TypeReference("Int", false, mutableListOf(), stubContext)

val constraintFunction = Function(
    kind = FunctionKind.FUNCTION,
    name = "constraintFunction",
    automatonReference = null,
    args = mutableListOf(
        FunctionArgument(
            name = "arg",
            typeReference = stubIntTypeReference,
            index = 0,
            entityPosition = stubPosition
        )
    ),
    returnType = stubIntTypeReference,
    context = FunctionContext(stubContext),
    isMethod = true,
    isStatic = false,
    entityPosition = stubPosition
)

// Our application state requirements
val constraints = listOf(
    VariableValueConstraint("intVar", IntConst(1)),
    VariableValueConstraint("strVar", StringConst("str")),
    VariableValueConstraint(
        "objVar",
        ObjectValue(
            listOf(
                VariableValueConstraint("innerIntVar", IntConst(42))
            )
        )
    ),
    FunctionResultConstraint(
        constraintFunction,
        mapOf("arg" to IntConst(10)),
        IntConst(20)
    )
)


