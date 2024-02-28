package com.spbpu

import org.jacodb.api.JcClassOrInterface
import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.nodes.Library
import org.usvm.api.decoder.ObjectData

class LibSLObjectDecoder(libslRepoPath: String) {

    val libsl = LibSL(libslRepoPath)

    fun <T : Any?> createInstance(
        type: JcClassOrInterface,
        objectData: ObjectData<T>,
    ): T {
        parseLibsl(type)

        TODO("Not yet implemented")
    }

    private fun parseLibsl(type: JcClassOrInterface): Library {
        val lslPath = resolveLibslPath(type)
        return libsl.loadFromFileName(lslPath)
    }

    private fun resolveLibslPath(type: JcClassOrInterface) =
        type.name.replace('.', '/') + ".lsl"
}
