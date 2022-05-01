package lesson4

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TrieTest : AbstractTrieTest() {

    override fun create(): MutableSet<String> =
        Trie()

    @Test
    @Tag("Example")
    fun generalTestJava() {
        doGeneralTest()
    }

    @Test
    @Tag("7")
    fun iteratorTestJava() {
        doIteratorTest()
        val trieSet = create()
        var iterator = trieSet.iterator()
        assertFalse(iterator.hasNext())
        assertFailsWith<NoSuchElementException> { iterator.next() }
        trieSet.add("abc")
        iterator = trieSet.iterator()
        assertTrue(iterator.hasNext())
        assertTrue(iterator.next() == "abc")
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
        val trieSet = create()
        trieSet.add("abc")
        val iterator = trieSet.iterator()
        iterator.next()
        iterator.remove()
        assertTrue(trieSet.size == 0)
    }
}