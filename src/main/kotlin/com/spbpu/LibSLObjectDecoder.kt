package com.spbpu

import org.jacodb.api.JcClassOrInterface
import org.jetbrains.research.libsl.LibSL
import org.usvm.api.decoder.DecoderApi
import org.usvm.api.decoder.ObjectData
import org.usvm.api.decoder.ObjectDecoder

class LibSLObjectDecoder(libslRepoPath: String) : ObjectDecoder {

    val libsl = LibSL(libslRepoPath)

    override fun <T : Any?> createInstance(
        p0: JcClassOrInterface?,
        p1: ObjectData<T>?,
        p2: DecoderApi<T>?
    ): T {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> initializeInstance(
        p0: JcClassOrInterface?,
        p1: ObjectData<T>?,
        p2: T,
        p3: DecoderApi<T>?
    ) {
        TODO("Not yet implemented")
    }
}
