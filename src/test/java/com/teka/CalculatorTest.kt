package com.teka

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CalculatorTest {

    @Test
    fun `Test calculator can add two numbers`() {
        val calculator = Calculator()
        val expected = 10
        assertEquals(expected, calculator.add(5, 5))
    }

    @Test
    fun `Test calculator can subtract two numbers`() {
        val calculator = Calculator()
        assertEquals(5, calculator.sub(15, 10))
    }
}