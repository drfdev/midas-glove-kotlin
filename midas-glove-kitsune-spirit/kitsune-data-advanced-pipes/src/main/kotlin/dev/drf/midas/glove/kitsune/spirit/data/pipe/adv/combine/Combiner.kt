package dev.drf.midas.glove.kitsune.spirit.data.pipe.adv.combine

import dev.drf.midas.glove.kitsune.spirit.data.pipe.DataPipe

interface Combiner<in T, out R> : DataPipe<T, R> {
    fun combineFrom(input: T, vararg directions: InputDirection): R

    fun directions(): Array<InputDirection>

    override fun transfer(input: T): R {
        val directions = directions();
        return combineFrom(input, *directions)
    }
}