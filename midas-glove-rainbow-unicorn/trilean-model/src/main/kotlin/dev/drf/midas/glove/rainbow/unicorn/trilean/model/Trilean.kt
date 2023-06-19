package dev.drf.midas.glove.rainbow.unicorn.trilean.model

class Trilean private constructor() : Comparable<Trilean> {
    companion object {
        val TRUE: Trilean = Trilean()
        val FALSE: Trilean = Trilean()
        val UNKNOWN: Trilean = Trilean()
    }

    infix fun and(other: Trilean): Trilean = and(this, other)
    infix fun or(other: Trilean): Trilean = or(this, other)
    infix fun xor(other: Trilean): Trilean = xor(this, other)

    operator fun not() = not(this)

    override fun compareTo(other: Trilean): Int = compare(this, other)

    override fun toString(): String = when (this) {
        TRUE -> "true"
        UNKNOWN -> "unknown"
        FALSE -> "false"
        else -> "wrong value"
    }
}