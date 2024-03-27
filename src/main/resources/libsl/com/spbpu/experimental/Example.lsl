libsl "1.1.0";

library experimental
    language "Java";

type Example is `com.spbpu.experimental.Example` {
}

automaton ExampleAutomaton(): Example {
    var x: int = 0;
    var y: int = 0;
    var z: int = 0;

    fun foo() {
        x += 5;
    }

    fun bar() {
        z = x + y;
    }

    fun baz() {
        y = 2 * x;
    }
}
