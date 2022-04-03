package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BinarySearchTreeTest : AbstractBinarySearchTreeTest() {

    override fun create(): CheckableSortedSet<Int> =
        BinarySearchTree()

    @Test
    @Tag("Example")
    fun initTestJava() {
        doInitTest()
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("Example")
    fun firstAndLastTestJava() {
        doFirstAndLastTest()
    }

    @Test
    @Tag("5")
    fun removeTestJava() {
        doRemoveTest()
        val binarySet = create()
        binarySet.add(0)
        assertFalse(binarySet.remove(null))
        assertFalse(binarySet.remove(1))
    }

    @Test
    @Tag("5")
    fun iteratorTestJava() {
        doIteratorTest()
        val binarySet = create()
        var iterator = binarySet.iterator()
        assertFalse(iterator.hasNext())
        assertFailsWith<NoSuchElementException> { iterator.next() }
        binarySet.add(1)
        iterator = binarySet.iterator()
        assertTrue(iterator.hasNext())
        assertTrue(iterator.next() == 1)
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
        val binarySet = create()
        binarySet.add(1)
        val iterator = binarySet.iterator()
        iterator.next()
        iterator.remove()
        assertTrue(binarySet.size == 0)
    }

    @Test
    @Tag("5")
    fun subSetTestJava() {
        doSubSetTest()
    }

    @Test
    @Tag("8")
    fun subSetRelationTestJava() {
        doSubSetRelationTest()
    }

    @Test
    @Tag("7")
    fun subSetFirstAndLastTestJava() {
        doSubSetFirstAndLastTest()
    }

    @Test
    @Tag("4")
    fun headSetTestJava() {
        doHeadSetTest()
    }

    @Test
    @Tag("7")
    fun headSetRelationTestJava() {
        doHeadSetRelationTest()
    }

    @Test
    @Tag("4")
    fun tailSetTestJava() {
        doTailSetTest()
    }

    @Test
    @Tag("7")
    fun tailSetRelationTestJava() {
        doTailSetRelationTest()
    }

}