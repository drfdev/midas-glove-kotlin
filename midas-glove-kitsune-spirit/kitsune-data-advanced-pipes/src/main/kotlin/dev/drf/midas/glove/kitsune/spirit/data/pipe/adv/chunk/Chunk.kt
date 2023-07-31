package dev.drf.midas.glove.kitsune.spirit.data.pipe.adv.chunk

interface Chunk<T> {
    fun info(): BlockInfo<T>
}