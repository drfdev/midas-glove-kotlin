package dev.drf.midas.glove.rainbow.unicorn.trilean.model

import kotlin.test.Test
import kotlin.test.assertEquals

class TrileanTest {
    // (p or q) and not(p and q)
    private fun xorCalculate(p: Trilean, q: Trilean): Trilean = (p or q) and !(p and q)

    // not(x) or y
    private fun materialConditionalCalculate(x: Trilean, y: Trilean): Trilean = !x or y

    // (x and y) or (not(x) and not(y))
    private fun materialBiconditionalCalculate(x: Trilean, y: Trilean): Trilean = (x and y) or (!x and !y)

    // not(x xor y)
    private fun logicalEquivalenceCalculate(x: Trilean, y: Trilean): Trilean = !(x xor y)

    @Test
    fun shouldCorrectAndOperation_whenLeftTrueAndRightTrue() {
        val left = Trilean.TRUE
        val right = Trilean.TRUE

        val result = left and right

        assertEquals(Trilean.TRUE, result)
    }

    @Test
    fun shouldCorrectAndOperation_whenLeftTrueAndRightUnknown() {
        val left = Trilean.TRUE
        val right = Trilean.UNKNOWN

        val result1 = left and right
        val result2 = right and left

        assertEquals(Trilean.UNKNOWN, result1)
        assertEquals(Trilean.UNKNOWN, result2)
    }

    @Test
    fun shouldCorrectAndOperation_whenLeftTrueAndRightFalse() {
        val left = Trilean.TRUE
        val right = Trilean.FALSE

        val result1 = left and right
        val result2 = right and left

        assertEquals(Trilean.FALSE, result1)
        assertEquals(Trilean.FALSE, result2)
    }

    @Test
    fun shouldCorrectAndOperation_whenLeftUnknownAndRightUnknown() {
        val left = Trilean.UNKNOWN
        val right = Trilean.UNKNOWN

        val result = left and right

        assertEquals(Trilean.UNKNOWN, result)
    }

    @Test
    fun shouldCorrectAndOperation_whenLeftUnknownAndRightFalse() {
        val left = Trilean.UNKNOWN
        val right = Trilean.FALSE

        val result1 = left and right
        val result2 = right and left

        assertEquals(Trilean.FALSE, result1)
        assertEquals(Trilean.FALSE, result2)
    }

    @Test
    fun shouldCorrectAndOperation_whenLeftFalseAndRightFalse() {
        val left = Trilean.FALSE
        val right = Trilean.FALSE

        val result = left and right

        assertEquals(Trilean.FALSE, result)
    }

    @Test
    fun shouldCorrectOrOperation_whenLeftTrueAndRightTrue() {
        val left = Trilean.TRUE
        val right = Trilean.TRUE

        val result = left or right

        assertEquals(Trilean.TRUE, result)
    }

    @Test
    fun shouldCorrectOrOperation_whenLeftTrueAndRightUnknown() {
        val left = Trilean.TRUE
        val right = Trilean.UNKNOWN

        val result1 = left or right
        val result2 = right or left

        assertEquals(Trilean.TRUE, result1)
        assertEquals(Trilean.TRUE, result2)
    }

    @Test
    fun shouldCorrectOrOperation_whenLeftTrueAndRightFalse() {
        val left = Trilean.TRUE
        val right = Trilean.FALSE

        val result1 = left or right
        val result2 = right or left

        assertEquals(Trilean.TRUE, result1)
        assertEquals(Trilean.TRUE, result2)
    }

    @Test
    fun shouldCorrectOrOperation_whenLeftUnknownAndRightUnknown() {
        val left = Trilean.UNKNOWN
        val right = Trilean.UNKNOWN

        val result = left or right

        assertEquals(Trilean.UNKNOWN, result)
    }

    @Test
    fun shouldCorrectOrOperation_whenLeftUnknownAndRightFalse() {
        val left = Trilean.UNKNOWN
        val right = Trilean.FALSE

        val result1 = left or right
        val result2 = right or left

        assertEquals(Trilean.UNKNOWN, result1)
        assertEquals(Trilean.UNKNOWN, result2)
    }

    @Test
    fun shouldCorrectOrOperation_whenLeftFalseAndRightFalse() {
        val left = Trilean.FALSE
        val right = Trilean.FALSE

        val result = left or right

        assertEquals(Trilean.FALSE, result)
    }

    @Test
    fun shouldCorrectXorOperation_whenLeftTrueAndRightTrue() {
        val left = Trilean.TRUE
        val right = Trilean.TRUE

        val result = left xor right
        val expected = xorCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectXorOperation_whenLeftTrueAndRightUnknown() {
        val left = Trilean.TRUE
        val right = Trilean.UNKNOWN

        val result1 = left xor right
        val result2 = right xor left

        val expected1 = xorCalculate(left, right)
        val expected2 = xorCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectXorOperation_whenLeftTrueAndRightFalse() {
        val left = Trilean.TRUE
        val right = Trilean.FALSE

        val result1 = left xor right
        val result2 = right xor left

        val expected1 = xorCalculate(left, right)
        val expected2 = xorCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectXorOperation_whenLeftUnknownAndRightUnknown() {
        val left = Trilean.UNKNOWN
        val right = Trilean.UNKNOWN

        val result = left xor right

        val expected = xorCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectXorOperation_whenLeftUnknownAndRightFalse() {
        val left = Trilean.UNKNOWN
        val right = Trilean.FALSE

        val result1 = left xor right
        val result2 = right xor left

        val expected1 = xorCalculate(left, right)
        val expected2 = xorCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectXorOperation_whenLeftFalseAndRightFalse() {
        val left = Trilean.FALSE
        val right = Trilean.FALSE

        val result = left xor right

        val expected = xorCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectMaterialConditionalOperation_whenLeftTrueAndRightTrue() {
        val left = Trilean.TRUE
        val right = Trilean.TRUE

        val result = materialConditional(left, right)
        val expected = materialConditionalCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectMaterialConditionalOperation_whenLeftTrueAndRightUnknown() {
        val left = Trilean.TRUE
        val right = Trilean.UNKNOWN

        val result1 = materialConditional(left, right)
        val result2 = materialConditional(right, left)

        val expected1 = materialConditionalCalculate(left, right)
        val expected2 = materialConditionalCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectMaterialConditionalOperation_whenLeftTrueAndRightFalse() {
        val left = Trilean.TRUE
        val right = Trilean.FALSE

        val result1 = materialConditional(left, right)
        val result2 = materialConditional(right, left)

        val expected1 = materialConditionalCalculate(left, right)
        val expected2 = materialConditionalCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectMaterialConditionalOperation_whenLeftUnknownAndRightUnknown() {
        val left = Trilean.UNKNOWN
        val right = Trilean.UNKNOWN

        val result = materialConditional(left, right)

        val expected = materialConditionalCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectMaterialConditionalOperation_whenLeftUnknownAndRightFalse() {
        val left = Trilean.UNKNOWN
        val right = Trilean.FALSE

        val result1 = materialConditional(left, right)
        val result2 = materialConditional(right, left)

        val expected1 = materialConditionalCalculate(left, right)
        val expected2 = materialConditionalCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectMaterialConditionalOperation_whenLeftFalseAndRightFalse() {
        val left = Trilean.FALSE
        val right = Trilean.FALSE

        val result = materialConditional(left, right)

        val expected = materialConditionalCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectMaterialBiconditionalOperation_whenLeftTrueAndRightTrue() {
        val left = Trilean.TRUE
        val right = Trilean.TRUE

        val result = materialBiconditional(left, right)
        val expected = materialBiconditionalCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectMaterialBiconditionalOperation_whenLeftTrueAndRightUnknown() {
        val left = Trilean.TRUE
        val right = Trilean.UNKNOWN

        val result1 = materialBiconditional(left, right)
        val result2 = materialBiconditional(right, left)

        val expected1 = materialBiconditionalCalculate(left, right)
        val expected2 = materialBiconditionalCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectMaterialBiconditionalOperation_whenLeftTrueAndRightFalse() {
        val left = Trilean.TRUE
        val right = Trilean.FALSE

        val result1 = materialBiconditional(left, right)
        val result2 = materialBiconditional(right, left)

        val expected1 = materialBiconditionalCalculate(left, right)
        val expected2 = materialBiconditionalCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectMaterialBiconditionalOperation_whenLeftUnknownAndRightUnknown() {
        val left = Trilean.UNKNOWN
        val right = Trilean.UNKNOWN

        val result = materialBiconditional(left, right)

        val expected = materialBiconditionalCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectMaterialBiconditionalOperation_whenLeftUnknownAndRightFalse() {
        val left = Trilean.UNKNOWN
        val right = Trilean.FALSE

        val result1 = materialBiconditional(left, right)
        val result2 = materialBiconditional(right, left)

        val expected1 = materialBiconditionalCalculate(left, right)
        val expected2 = materialBiconditionalCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectMaterialBiconditionalOperation_whenLeftFalseAndRightFalse() {
        val left = Trilean.FALSE
        val right = Trilean.FALSE

        val result = materialBiconditional(left, right)

        val expected = materialBiconditionalCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectLogicalEquivalenceOperation_whenLeftTrueAndRightTrue() {
        val left = Trilean.TRUE
        val right = Trilean.TRUE

        val result = logicalEquivalence(left, right)
        val expected = logicalEquivalenceCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectLogicalEquivalenceOperation_whenLeftTrueAndRightUnknown() {
        val left = Trilean.TRUE
        val right = Trilean.UNKNOWN

        val result1 = logicalEquivalence(left, right)
        val result2 = logicalEquivalence(right, left)

        val expected1 = logicalEquivalenceCalculate(left, right)
        val expected2 = logicalEquivalenceCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectLogicalEquivalenceOperation_whenLeftTrueAndRightFalse() {
        val left = Trilean.TRUE
        val right = Trilean.FALSE

        val result1 = logicalEquivalence(left, right)
        val result2 = logicalEquivalence(right, left)

        val expected1 = logicalEquivalenceCalculate(left, right)
        val expected2 = logicalEquivalenceCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectLogicalEquivalenceOperation_whenLeftUnknownAndRightUnknown() {
        val left = Trilean.UNKNOWN
        val right = Trilean.UNKNOWN

        val result = logicalEquivalence(left, right)

        val expected = logicalEquivalenceCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCorrectLogicalEquivalenceOperation_whenLeftUnknownAndRightFalse() {
        val left = Trilean.UNKNOWN
        val right = Trilean.FALSE

        val result1 = logicalEquivalence(left, right)
        val result2 = logicalEquivalence(right, left)

        val expected1 = logicalEquivalenceCalculate(left, right)
        val expected2 = logicalEquivalenceCalculate(right, left)

        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    fun shouldCorrectLogicalEquivalenceOperation_whenLeftFalseAndRightFalse() {
        val left = Trilean.FALSE
        val right = Trilean.FALSE

        val result = logicalEquivalence(left, right)

        val expected = logicalEquivalenceCalculate(left, right)

        assertEquals(expected, result)
    }

    @Test
    fun shouldCompareForTrueValue() {
        val value = Trilean.TRUE

        val resultWithTrue = value.compareTo(Trilean.TRUE)
        val resultWithUnknown = value.compareTo(Trilean.UNKNOWN)
        val resultWithFalse = value.compareTo(Trilean.FALSE)

        assertEquals(0, resultWithTrue)
        assertEquals(-1, resultWithUnknown)
        assertEquals(-1, resultWithFalse)
    }

    @Test
    fun shouldCompareForUnknownValue() {
        val value = Trilean.UNKNOWN

        val resultWithTrue = value.compareTo(Trilean.TRUE)
        val resultWithUnknown = value.compareTo(Trilean.UNKNOWN)
        val resultWithFalse = value.compareTo(Trilean.FALSE)

        assertEquals(1, resultWithTrue)
        assertEquals(0, resultWithUnknown)
        assertEquals(-1, resultWithFalse)
    }

    @Test
    fun shouldCompareForFalseValue() {
        val value = Trilean.FALSE

        val resultWithTrue = value.compareTo(Trilean.TRUE)
        val resultWithUnknown = value.compareTo(Trilean.UNKNOWN)
        val resultWithFalse = value.compareTo(Trilean.FALSE)

        assertEquals(1, resultWithTrue)
        assertEquals(1, resultWithUnknown)
        assertEquals(0, resultWithFalse)
    }
}