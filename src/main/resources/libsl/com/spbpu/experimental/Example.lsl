libsl "1.1.0";

library experimental
    language "Kotlin";

type Example is com.spbpu.experimental.Example {
}

automaton ExampleAutomaton(): Example {
    var x: int = 0;
    var y: int = 0;
    var z: int = 0;

    fun foo(): void {
        x += 5;
    }

    fun bar(): void {
        y = x + z;
    }

    fun baz(value: int): void {
        if (value < 0) {
            z = _neg(value);
        } else {
            z = value;
        }
    }

    proc _neg(value: int): int {
        result = -value;
    }
}
