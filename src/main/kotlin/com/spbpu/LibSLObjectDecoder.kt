package com.spbpu

import org.jacodb.api.JcClassOrInterface
import org.jacodb.api.JcClasspath
import org.jetbrains.research.libsl.LibSL
import org.jetbrains.research.libsl.nodes.Library
import org.usvm.api.decoder.DecoderApi
import org.usvm.api.decoder.ObjectData
import org.usvm.api.decoder.ObjectDecoder

class LibSLObjectDecoder(libslRepoPath: String) : ObjectDecoder {

    val libsl = LibSL(libslRepoPath)

    override fun <T : Any?> createInstance(
        type: JcClassOrInterface,
        objectData: ObjectData<T>,
        decoder: DecoderApi<T>
    ): T {
        parseLibsl(type.classpath)

        TODO("Not yet implemented")
    }

    override fun <T : Any?> initializeInstance(
        type: JcClassOrInterface,
        objectData: ObjectData<T>,
        instance: T,
        decoder: DecoderApi<T>
    ) {
        TODO("Not yet implemented")
    }

    private fun parseLibsl(classpath: JcClasspath): Library {
        return libsl.loadFromFileName("sample.lsl")
    }
}
