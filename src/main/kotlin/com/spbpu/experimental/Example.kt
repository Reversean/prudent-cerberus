package com.spbpu.experimental

class Example {
    var x: Int = 0
    var y: Int = 0
    var z: Int = 0

    fun foo() {
        x += 5
    }

    fun bar() {
        z = x + y
    }

    fun baz() {
        y = 2 * x
    }
}
