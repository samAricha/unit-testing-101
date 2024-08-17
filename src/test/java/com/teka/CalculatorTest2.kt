package com.teka

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CalculatorTest2 {

    @Test
    fun add() {
        val calculator = Calculator()
        val expected = 10
        assertEquals(expected, calculator.add(5, 5))
    }

    @Test
    fun sub() {
        val calculator = Calculator()
        val expected = 7
        assertEquals(expected, calculator.sub(12, 7))
    }
}