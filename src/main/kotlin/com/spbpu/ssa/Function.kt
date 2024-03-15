package com.spbpu.ssa

open class Function(
    name: String,
    val body: FunctionBody,
    val args: List<FunctionArgument> = emptyList()
) : Node(name) {
}

class Constructor(
    name: String,
    body: FunctionBody,
    args: List<FunctionArgument>
) : Function(name, body, args) {
}
