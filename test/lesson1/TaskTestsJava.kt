package lesson1

import org.junit.jupiter.api.Tag
import java.io.File
import kotlin.test.Test

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("3")
    fun testSortTimesJava() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
        try {
            JavaTasks.sortTimes("input/time_in_mine1.txt", "temp.txt")
            assertFileContent("temp.txt", "")
        } finally {
            File("temp.txt").delete()
        }
        try {
            JavaTasks.sortTimes("input/time_in_mine2.txt", "temp.txt")
            assertFileContent(
                "temp.txt",
                """
                    12:00:00 AM
                    12:00:00 AM
                    12:00:00 AM
                    12:00:00 AM
                    12:00:00 AM
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    @Tag("4")
    fun testSortAddressesJava() {
        sortAddresses { inputName, outputName -> JavaTasks.sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortTemperaturesJava() {
        sortTemperatures { inputName, outputName -> JavaTasks.sortTemperatures(inputName, outputName) }
        try {
            JavaTasks.sortTemperatures("input/temp_in_mine1.txt", "temp.txt")
            assertFileContent("temp.txt", "")
        } finally {
            File("temp.txt").delete()
        }
        try {
            JavaTasks.sortTemperatures("input/temp_in_mine2.txt", "temp.txt")
            assertFileContent(
                "temp.txt",
                """
                    0.0
                    0.0
                    0.0
                    0.0
                    0.0
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    @Tag("4")
    fun testSortSequenceJava() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
    }

    @Test
    @Tag("2")
    fun testMergeArraysJava() {
        mergeArrays { first, second -> JavaTasks.mergeArrays<Int?>(first, second) }
    }
}
