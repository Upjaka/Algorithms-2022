package lesson7

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("5")
    fun testLongestCommonSubSequenceJava() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("7")
    fun testLongestIncreasingSubSequenceJava() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
        assertEquals(listOf(3, 3, 3), JavaDynamicTasks.longestIncreasingSubSequence(listOf(3, 2, 4, 3, 3)))
        assertEquals(listOf(1, 4, 6, 7), JavaDynamicTasks.longestIncreasingSubSequence(listOf(5, 8, 9, 1, 4, 6, 7)))
    }

    @Test
    @Tag("4")
    fun testShortestPathOnFieldJava() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
        assertEquals(0, JavaDynamicTasks.shortestPathOnField("input/field_in7.txt"))
        assertEquals(0, JavaDynamicTasks.shortestPathOnField("input/field_in8.txt"))
    }
}