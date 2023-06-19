package dev.drf.midas.glove.rainbow.unicorn.trilean.model

// Trilean logic:
// https://en.wikipedia.org/wiki/Three-valued_logic
// https://en.wikipedia.org/wiki/Boolean_algebra#Boolean_operations

fun isTrue(value: Trilean): Boolean = value == Trilean.TRUE
fun isFalse(value: Trilean): Boolean = value == Trilean.FALSE
fun isUnknown(value: Trilean): Boolean = value == Trilean.UNKNOWN

fun not(value: Trilean): Trilean = when (value) {
    Trilean.TRUE -> Trilean.FALSE
    Trilean.UNKNOWN -> Trilean.UNKNOWN
    Trilean.FALSE -> Trilean.TRUE
    else -> throw IllegalArgumentException()
}

fun and(left: Trilean, right: Trilean): Trilean = when (left) {
    Trilean.TRUE -> when (right) {
        Trilean.TRUE -> Trilean.TRUE
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        Trilean.TRUE -> Trilean.UNKNOWN
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        Trilean.TRUE -> Trilean.FALSE
        Trilean.UNKNOWN -> Trilean.FALSE
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

fun or(left: Trilean, right: Trilean): Trilean = when (left) {
    Trilean.TRUE -> when (right) {
        Trilean.TRUE -> Trilean.TRUE
        Trilean.UNKNOWN -> Trilean.TRUE
        Trilean.FALSE -> Trilean.TRUE
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        Trilean.TRUE -> Trilean.TRUE
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.UNKNOWN
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        Trilean.TRUE -> Trilean.TRUE
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

/**
 * https://en.wikipedia.org/wiki/Exclusive_or
 * p xor q = (p or q) and not(p and q)
 */
fun xor(left: Trilean, right: Trilean): Trilean = when (left) {
    Trilean.TRUE -> when (right) {
        // (true or true) and not(true and true)
        // true and not(true)
        // true and false
        Trilean.TRUE -> Trilean.FALSE
        // (true or unknown) and not(true and unknown)
        // true and not(unknown)
        // true and unknown
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // (true or false) and not(true and false)
        // true and not(false)
        // true and true
        Trilean.FALSE -> Trilean.TRUE
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        // (unknown or true) and not(unknown and true)
        // true and not(unknown)
        // true and unknown
        Trilean.TRUE -> Trilean.UNKNOWN
        // (unknown or unknown) and not(unknown and unknown)
        // unknown and not(unknown)
        // unknown and unknown
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // (unknown or false) and not(unknown and false)
        // unknown and not(false)
        // unknown and true
        Trilean.FALSE -> Trilean.UNKNOWN
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        // (false or true) and not(false and true)
        // true and not(false)
        // true and true
        Trilean.TRUE -> Trilean.TRUE
        // (false or unknown) and not(false and unknown)
        // unknown and not(false)
        // unknown and true
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // (false or false) and not(false and false)
        // false and not(false)
        // false and true
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

/**
 * https://en.wikipedia.org/wiki/Material_conditional
 * x MC y = not(x) or y
 */
fun materialConditional(left: Trilean, right: Trilean): Trilean = when (left) {
    Trilean.TRUE -> when (right) {
        // not(true) or true
        // false or true
        Trilean.TRUE -> Trilean.TRUE
        // not(true) or unknown
        // false or unknown
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // not(true) or false
        // false or false
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        // not(unknown) or true
        // unknown or true
        Trilean.TRUE -> Trilean.TRUE
        // not(unknown) or unknown
        // unknown or unknown
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // not(unknown) or false
        // unknown or false
        Trilean.FALSE -> Trilean.UNKNOWN
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        // not(false) or true
        // true or true
        Trilean.TRUE -> Trilean.TRUE
        // not(false) or unknown
        // true or unknown
        Trilean.UNKNOWN -> Trilean.TRUE
        // not(false) or false
        // true or false
        Trilean.FALSE -> Trilean.TRUE
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

/**
 * https://en.wikipedia.org/wiki/Logical_biconditional
 * x MB y = (x and y) or (not(x) and not(y))
 */
fun materialBiconditional(left: Trilean, right: Trilean): Trilean = when (left) {
    Trilean.TRUE -> when (right) {
        // (true and true) or (not(true) and not(true))
        // true or (false and false)
        // true or false
        Trilean.TRUE -> Trilean.TRUE
        // (true and unknown) or (not(true) and not(unknown))
        // unknown or (false and unknown)
        // unknown or false
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // (true and false) or (not(true) and not(false))
        // false or (false and true)
        // false or false
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        // (unknown and true) or (not(unknown) and not(true))
        // unknown or (unknown and false)
        // unknown or false
        Trilean.TRUE -> Trilean.UNKNOWN
        // (unknown and unknown) or (not(unknown) and not(unknown))
        // unknown or (unknown and unknown)
        // unknown or unknown
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // (unknown and false) or (not(unknown) and not(false))
        // false or (unknown and true)
        // false or unknown
        Trilean.FALSE -> Trilean.UNKNOWN
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        // (false and true) or (not(false) and not(true))
        // false or (true and false)
        // false or false
        Trilean.TRUE -> Trilean.FALSE
        // (false and unknown) or (not(false) and not(unknown))
        // false or (true and unknown)
        // false or unknown
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        // (false and false) or (not(false) and not(false))
        // false or (true and true)
        // false or true
        Trilean.FALSE -> Trilean.TRUE
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

/**
 * https://en.wikipedia.org/wiki/Logical_equivalence
 * x LE y = not(x xor y)
 */
fun logicalEquivalence(left: Trilean, right: Trilean): Trilean = when (left) {
    Trilean.TRUE -> when (right) {
        Trilean.TRUE -> Trilean.TRUE
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.FALSE
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        Trilean.TRUE -> Trilean.UNKNOWN
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.UNKNOWN
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        Trilean.TRUE -> Trilean.FALSE
        Trilean.UNKNOWN -> Trilean.UNKNOWN
        Trilean.FALSE -> Trilean.TRUE
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

fun compare(left: Trilean, right: Trilean): Int = when (left) {
    Trilean.TRUE -> when (right) {
        Trilean.TRUE -> 0
        Trilean.UNKNOWN -> -1
        Trilean.FALSE -> -1
        else -> throw IllegalArgumentException()
    }

    Trilean.UNKNOWN -> when (right) {
        Trilean.TRUE -> 1
        Trilean.UNKNOWN -> 0
        Trilean.FALSE -> -1
        else -> throw IllegalArgumentException()
    }

    Trilean.FALSE -> when (right) {
        Trilean.TRUE -> 1
        Trilean.UNKNOWN -> 1
        Trilean.FALSE -> 0
        else -> throw IllegalArgumentException()
    }

    else -> throw IllegalArgumentException()
}

