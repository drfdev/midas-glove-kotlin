package dev.drf.midas.glove.kitsune.spirit.data.pipe.adv.chunk

interface Block<T> {
    fun append(chunk: Chunk<T>): Boolean

    fun build(): T
}