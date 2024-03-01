package com.spbpu

import io.ksmt.KContext
import io.ksmt.expr.KApp
import io.ksmt.expr.KExpr
import io.ksmt.solver.z3.KZ3Solver
import io.ksmt.sort.KBoolSort
import io.ksmt.sort.KIntSort
import io.ksmt.utils.getValue
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

interface ApiConstraint {
    fun toExpr(args: ApiConstraintArgs): KExpr<KBoolSort>
}

data class ApiConstraintArgs(val layer: Int)

fun main() {
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
