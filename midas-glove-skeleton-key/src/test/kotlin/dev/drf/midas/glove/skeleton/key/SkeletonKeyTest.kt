package dev.drf.midas.glove.skeleton.key

import kotlin.test.Test
import kotlin.test.assertNotNull

class SkeletonKeyTest {
    @Test
    fun testSkeletonKey() {
        val value = SkeletonKey()
        assertNotNull(value)
    }
}