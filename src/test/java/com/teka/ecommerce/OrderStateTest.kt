package com.teka.ecommerce

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OrderStateTest {
    private val LAPTOP = "Macbook"
    private val MOUSE = "Logitech Mouse"
    private val warehouse = Warehouse()

    @BeforeEach
    fun setUp() {
        warehouse.add(LAPTOP, 50)
        warehouse.add(MOUSE, 20)
    }

    @Test
    fun `test order is fulfilled if capacity in warehouse is sufficient`() {
        val order = Order(LAPTOP, 20)
        order.fulFill(warehouse)
        assertTrue(order.isFulfilled())
        assertEquals(30, warehouse.getInventory(LAPTOP))
    }

    @Test
    fun `test order is not fulfilled if capacity in warehouse is insufficient`() {
        val order = Order(MOUSE, 21)
        order.fulFill(warehouse)
        assertFalse(order.isFulfilled())
        assertEquals(20, warehouse.getInventory(MOUSE))
    }

}