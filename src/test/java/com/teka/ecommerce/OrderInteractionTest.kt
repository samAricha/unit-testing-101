package com.teka.ecommerce

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OrderInteractionTest {
    private val LAPTOP = "Macbook"

    @Test
    fun `test warehouse capacity is reduced on fulfilling order`() {
        val order = Order(product = LAPTOP, quantity =  50)
        val warehouseMock = mock(Warehouse::class.java)

        `when`(warehouseMock.getInventory(product = LAPTOP)).thenReturn(0)
        `when`(warehouseMock.remove(product = LAPTOP, quantity = 50)).thenReturn(true)

        order.fulFill(warehouseMock)
        assertTrue(order.isFulfilled())
        assertEquals(0, warehouseMock.getInventory(LAPTOP))
    }

    @Test
    fun `test warehouse capacity is not reduced when order cannot be fulfilled`() {
        //our test variables
        val itemsInStock = 50
        val orderQuantity = 50

        val order = Order(LAPTOP, orderQuantity)
        val warehouseMock = mock(Warehouse::class.java)
        `when`(warehouseMock.getInventory(LAPTOP)).thenReturn(itemsInStock)
        `when`(warehouseMock.remove(LAPTOP, orderQuantity)).thenReturn(false)

        // Attempt to fulfill the order
        order.fulFill(warehouseMock)


        // Verify that the inventory remains unchanged
//        verify(warehouseMock).getInventory(LAPTOP)
        verify(warehouseMock).remove(LAPTOP, orderQuantity)

        println(warehouseMock.getInventory(LAPTOP))

        // Assert that the order is not fulfilled
        assertFalse(order.isFulfilled(), "Order should not be fulfilled when ordered quantity exceeds available stock.")
        assertEquals(itemsInStock, warehouseMock.getInventory(LAPTOP))
    }
}