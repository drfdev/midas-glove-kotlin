package dev.drf.midas.glove.kitsune.spirit.data.pipe.adv.switcher

import dev.drf.midas.glove.kitsune.spirit.data.pipe.DataPipe

interface Switcher<in T, out R> : DataPipe<T, R> {
    fun switchTo(input: T, direction: OutputDirection): R

    fun direction(): OutputDirection

    override fun transfer(input: T): R {
        val direction = direction();
        return switchTo(input, direction)
    }
}