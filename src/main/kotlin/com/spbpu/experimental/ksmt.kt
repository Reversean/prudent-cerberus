package com.spbpu.experimental

import io.ksmt.KContext
import io.ksmt.expr.KApp
import io.ksmt.expr.KExpr
import io.ksmt.solver.z3.KZ3SMTLibParser
import io.ksmt.solver.z3.KZ3Solver
import io.ksmt.sort.KBoolSort
import io.ksmt.sort.KIntSort
import io.ksmt.utils.getValue
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

data class ApiConstraintArgs(val layer: Int)

interface ApiConstraint {
    fun toExpr(args: ApiConstraintArgs): KExpr<KBoolSort>
}

fun old() {
    // max call stack depth
    val depth = 3

    // api constraints
    val apiConstraints: List<ApiConstraint> = emptyList()

    val ctx = KContext()
    with(ctx) {

        // api call combination iterator
        val i by intSort

        // target object predicate
        val goal by boolSort

        KZ3Solver(this).use { solver ->
            solver.assert(i lt apiConstraints.size.expr.power(depth.expr))
            for (layer in 0 until depth) {
                getLayerConstraint(i, layer, apiConstraints)?.let { layerConstraint ->
                    solver.assert(goal eq layerConstraint)
                }
            }
            solver.check(timeout = 5.seconds)
        }
    }
}

fun KContext.getLayerConstraint(
    i: KApp<KIntSort, *>,
    layer: Int,
    apiConstraints: List<ApiConstraint>
): KExpr<KBoolSort>? {
    var result: KExpr<KBoolSort>? = null
    val args = ApiConstraintArgs(layer)
    val currentIndex = i mod (apiConstraints.size pow layer).expr
    for ((apiIndex, api) in apiConstraints.withIndex()) {
        val expr = api.toExpr(args)
        if (result == null) {
            result = expr
            continue
        }

        val cond = currentIndex eq apiIndex.expr
        result = mkIte(cond, expr, result)
    }
    return result
}

infix fun Int.pow(other: Int) = this.toDouble().pow(other).toInt()

fun main() {
    val ctx = KContext()
    with(ctx) {

        val x0 by intSort    // from xInit
        val y0 by intSort    // from yInit
        val z0 by intSort    // from zInit

        val x1 by intSort    // to xFinal
        val y1 by intSort    // to yFinal
        val z1 by intSort    // to zFinal

        val initState = listOf(x0, y0, z0)
        val finalState = listOf(x1, y1, z1)

        val instructions = listOf(
            x1 eq (x0 + 5.expr),    // x += 5
            y1 eq (2.expr + x1),    // y = 2 * x
            x1 eq (x1 + y1)         // z = x + y
        )

        KZ3Solver(this).use { solver ->
//            beforeMethod(initState)
            instructions.forEach(solver::assert)
//            afterMethod(finalState)
            solver.check(timeout = 5.seconds)
        }
    }
}