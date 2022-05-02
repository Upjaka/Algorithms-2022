package lesson5

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OpenAddressingSetTest : AbstractOpenAddressingSetTest() {

    override fun <T : Any> create(bits: Int): MutableSet<T> {
        return OpenAddressingSet(bits)
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("7")
    fun removeTestJava() {
        doRemoveTest()
        val openAddressingSet = create<Int>(3)
        openAddressingSet.add(0)
        assertFalse(openAddressingSet.remove(1))
        assertTrue(openAddressingSet.remove(0))
        assertTrue(openAddressingSet.size == 0)
    }

    @Test
    @Tag("5")
    fun iteratorTestJava() {
        doIteratorTest()
        val openAddressingSet = create<String>(5)
        var iterator = openAddressingSet.iterator()
        assertFalse(iterator.hasNext())
        assertFailsWith<NoSuchElementException> { iterator.next() }
        openAddressingSet.add("abc")
        iterator = openAddressingSet.iterator()
        assertTrue(iterator.hasNext())
        assertTrue(iterator.next() == "abc")
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
        val openAddressingSet = create<String>(5)
        openAddressingSet.add("abc")
        val iterator = openAddressingSet.iterator()
        iterator.next()
        iterator.remove()
        assertTrue(openAddressingSet.size == 0)
        assertFalse(openAddressingSet.contains("abc"))
    }
}