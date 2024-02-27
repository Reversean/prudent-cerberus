libsl "1.1.0";

library foo
    language "Java";

import java.common;

type Foo is `com.spbpu.Foo` {
}

automaton FooAutomaton(
    var a: int,
): Foo {

    initstate Initialized;

    shift Initialized -> self by [
        setB,
    ];

    var b: int = 0;

    fun setB(value: int) {
        b = value;
    }
}
