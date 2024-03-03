package com.spbpu

import io.ksmt.expr.KExpr
import io.ksmt.sort.KBoolSort

interface ApiConstraint {
    fun toExpr(args: ApiConstraintArgs): KExpr<KBoolSort>
}
