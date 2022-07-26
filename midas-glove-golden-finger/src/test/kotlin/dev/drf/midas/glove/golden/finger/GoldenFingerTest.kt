package dev.drf.midas.glove.golden.finger

import kotlin.test.Test
import kotlin.test.assertNotNull

class GoldenFingerTest {
    @Test
    fun testGoldenFinger() {
        val value = GoldenFinger()
        assertNotNull(value)
    }
}