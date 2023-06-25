package dev.drf.midas.glove.kitsune.spirit.data.pipe

interface DataPipe<in T, out R> {
    fun transfer(input: T): R
}