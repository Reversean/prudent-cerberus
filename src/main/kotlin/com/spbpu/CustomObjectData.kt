package com.spbpu

import org.jacodb.api.JcField
import org.usvm.api.SymbolicIdentityMap
import org.usvm.api.SymbolicList
import org.usvm.api.SymbolicMap
import org.usvm.api.decoder.ObjectData

class CustomObjectData<T>(
    val obj: T
): ObjectData<T> {
    override fun decodeField(field: JcField): T {
        TODO("Not yet implemented")
    }

    override fun decodeSymbolicListField(p0: JcField?): SymbolicList<T> {
        TODO("Not yet implemented")
    }

    override fun decodeSymbolicMapField(p0: JcField?): SymbolicMap<T, T> {
        TODO("Not yet implemented")
    }

    override fun decodeSymbolicIdentityMapField(p0: JcField?): SymbolicIdentityMap<T, T> {
        TODO("Not yet implemented")
    }

    override fun getObjectField(p0: JcField?): ObjectData<T> {
        TODO("Not yet implemented")
    }

    override fun getBooleanField(p0: JcField?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getByteField(p0: JcField?): Byte {
        TODO("Not yet implemented")
    }

    override fun getShortField(p0: JcField?): Short {
        TODO("Not yet implemented")
    }

    override fun getIntField(p0: JcField?): Int {
        TODO("Not yet implemented")
    }

    override fun getLongField(p0: JcField?): Long {
        TODO("Not yet implemented")
    }

    override fun getFloatField(p0: JcField?): Float {
        TODO("Not yet implemented")
    }

    override fun getDoubleField(p0: JcField?): Double {
        TODO("Not yet implemented")
    }

    override fun getCharField(p0: JcField?): Char {
        TODO("Not yet implemented")
    }

    override fun getArrayFieldLength(p0: JcField?): Int {
        TODO("Not yet implemented")
    }
}