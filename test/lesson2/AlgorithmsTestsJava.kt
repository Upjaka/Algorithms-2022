package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals


class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("2")
    fun testOptimizeBuyAndSellJava() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
        assertEquals(0 to 0, JavaAlgorithms.optimizeBuyAndSell("input/buysell_in_mine.txt"))
        assertEquals(0 to 0, JavaAlgorithms.optimizeBuyAndSell("input/buysell_in_mine2.txt"))
    }

    @Test
    @Tag("2")
    fun testJosephTaskJava() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("4")
    fun testLongestCommonSubstringJava() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("3")
    fun testCalcPrimesNumberJava() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
        assertEquals(0, JavaAlgorithms.calcPrimesNumber(-100))
        assertEquals(0, JavaAlgorithms.calcPrimesNumber(0))

    }
}