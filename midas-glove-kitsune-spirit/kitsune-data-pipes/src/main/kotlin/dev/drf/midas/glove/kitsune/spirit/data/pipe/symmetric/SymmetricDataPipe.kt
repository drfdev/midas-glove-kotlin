package dev.drf.midas.glove.kitsune.spirit.data.pipe.symmetric

import dev.drf.midas.glove.kitsune.spirit.data.pipe.DataPipe

interface SymmetricDataPipe<T> : DataPipe<T, T> {
    fun forward(input: T): T
    fun backward(input: T): T

    fun direction(): FlowDirection

    override fun transfer(input: T): T {
        val direction = direction()
        return if (direction == FlowDirection.FORWARD)
            forward(input)
        else
            backward(input)
    }
}