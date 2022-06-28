package com.example.weatherappcat25

import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class FirstTestingTest {

    private lateinit var objectTest: FirstTesting

    @Before
    fun setUp() {
        objectTest = FirstTesting()
    }

    @After
    fun tearDown() {
        // clear data you no need anymore
        // clear mock objects
    }

    @Test
    fun `addition for two numbers when both are positive it will return a positive number`() {
        // AAA
        // Assign
        val firstNumber = 6.0
        val secondNumber = 1.0

        // Action
        val result = objectTest.addition(firstNumber, secondNumber)

        // Assertion
        assertEquals(7.0, result)
    }

    @Test
    fun `addition for two numbers when one negative another positive number being negative greater than positive returns negative number`() {
        // AAA
        // Assign
        val firstNumber = 6.0
        val secondNumber = -7.0

        // Action
        val result = objectTest.addition(firstNumber, secondNumber)

        // Assertion
        assertEquals(-1.0, result)
    }

    @Test
    fun `addition for two numbers when both are negative returns a negative number`() {
        // AAA
        // Assign
        val firstNumber = -6.0
        val secondNumber = -4.0

        // Action
        val result = objectTest.addition(firstNumber, secondNumber)

        // Assertion
        assertEquals(-10.0, result)
    }


    @Test
    fun `divide 2 numbers when both are positive returns positive number`() {
        // AAA
        // Assign
        val firstNumber = 6.0
        val secondNumber = 2.0

        // Action
        val result = objectTest.divide(firstNumber, secondNumber)

        // Assertion
        assertEquals(3.0, result)
    }

    @Test
    fun `divide 2 numbers when denominator is zero returns POSITIVE INFINITY`() {
        // AAA
        // Assign
        val firstNumber = 6.0
        val secondNumber = 0.0

        // Action
        val result = objectTest.divide(firstNumber, secondNumber)

        // Assertion
        assertEquals(Double.POSITIVE_INFINITY, result)
    }

    @Test
    fun `divide 2 numbers when numerator is zero returns ZERO as result`() {
        // AAA
        // Assign
        val firstNumber = 0.0
        val secondNumber = 4.0

        // Action
        val result = objectTest.divide(firstNumber, secondNumber)

        // Assertion
        assertEquals(0.0, result)
    }


}