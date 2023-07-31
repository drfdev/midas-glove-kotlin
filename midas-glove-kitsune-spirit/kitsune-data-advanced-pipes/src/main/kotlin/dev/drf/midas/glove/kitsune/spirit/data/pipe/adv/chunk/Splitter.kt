package dev.drf.midas.glove.kitsune.spirit.data.pipe.adv.chunk

interface Splitter<T> {
    fun split(value: T, info: BlockInfo<T>): ChunkPipe<T>

    fun infoFrom(value: T): BlockInfo<T>
}