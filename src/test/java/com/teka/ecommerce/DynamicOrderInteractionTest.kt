package com.teka.ecommerce

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DynamicOrderInteractionTest {
    private val LAPTOP = "Macbook"

    @Test
    fun `test order fulfillment with insufficient stock`() {
        testOrderFulfillmentWithInsufficientStock(availableStock = 60, orderedQuantity = 45)
    }


    private fun testOrderFulfillmentWithInsufficientStock(availableStock: Int, orderedQuantity: Int) {
        // Create an order with the specified quantity
        val order = Order(LAPTOP, orderedQuantity)

        // Mock the Warehouse
        val warehouseMock = mock(Warehouse::class.java)

        // Set up the mock to return the available stock for the product
        `when`(warehouseMock.getInventory(LAPTOP)).thenReturn(availableStock)

        // **Faulty Mock Behavior**: Simulate a scenario where the remove method incorrectly returns true
        `when`(warehouseMock.remove(product = LAPTOP, quantity = orderedQuantity)).thenReturn(true)

        // Attempt to fulfill the order
        order.fulFill(warehouseMock)

        // Assert that the order is not fulfilled if ordered quantity is more than available stock
        if (orderedQuantity > availableStock) {
            // This assertion will fail because the faulty mock returns true, making it incorrectly fulfilled
            assertFalse(order.isFulfilled(), "Order should not be fulfilled when requested quantity exceeds available stock.")
            // Verify that the inventory is unchanged
            verify(warehouseMock).getInventory(LAPTOP)
            assertEquals(availableStock, warehouseMock.getInventory(LAPTOP), "Inventory should remain unchanged.")
        } else {
            // This assertion will pass if orderedQuantity <= availableStock, so it’s not the focus here
            assertTrue(order.isFulfilled(), "Order should be fulfilled when requested quantity does not exceed available stock.")
            // Verify that the inventory has been decremented (assuming remove logic is correct)
            verify(warehouseMock).remove(product = LAPTOP, quantity = orderedQuantity)
        }
    }
}