package lesson6

import lesson6.impl.GraphBuilder
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GraphTestsJava : AbstractGraphTests() {
    @Test
    @Tag("6")
    fun testFindEulerLoopJava() {
        findEulerLoop { let { JavaGraphTasks.findEulerLoop(it) } }
        // Мои тесты в классе AbstractGraphTests
    }

    @Test
    @Tag("7")
    fun testMinimumSpanningTreeJava() {
        minimumSpanningTree { let { JavaGraphTasks.minimumSpanningTree(it) } }
        val graph = GraphBuilder().apply {
            addVertex("A")
        }.build()
        val tree = JavaGraphTasks.minimumSpanningTree(graph)
        assertEquals(0, tree.edges.size)
        val graph2 = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            addConnection(a, b)
            addConnection(a, c)
            addConnection(a, d)
            addConnection(b, c)
            addConnection(b, d)
            addConnection(c, d)
        }.build()
        val tree2 = JavaGraphTasks.minimumSpanningTree(graph2)
        assertEquals(3, tree2.edges.size)
    }

    @Test
    @Tag("10")
    fun testLargestIndependentVertexSetJava() {
        largestIndependentVertexSet { let { JavaGraphTasks.largestIndependentVertexSet(it) } }
    }

    @Test
    @Tag("8")
    fun testLongestSimplePathJava() {
        longestSimplePath { let { JavaGraphTasks.longestSimplePath(it) } }
    }

    @Test
    @Tag("6")
    fun testBaldaSearcherJava() {
        baldaSearcher { inputName, words -> JavaGraphTasks.baldaSearcher(inputName, words) }
    }
}