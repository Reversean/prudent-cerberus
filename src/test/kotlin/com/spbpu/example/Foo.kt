package com.spbpu.example

class Foo(val a: Int) {
    private var b = 0
    private var c = 0
    fun setB(value: Int) {
        b = value
    }
    fun incC() {
        ++c
    }
}
