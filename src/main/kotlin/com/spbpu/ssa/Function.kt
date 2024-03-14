package com.spbpu.ssa

sealed class Function(
    name: String,
    val args: List<FunctionArgument>
) : Node(name) {
}

class Constructor(
    name: String,
    args: List<FunctionArgument>
) : Function(name, args) {
}
