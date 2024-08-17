package com.teka.ecommerce

class Order(private val product: String, private val quantity: Int) {
    private lateinit var warehouse: Warehouse
    private var isFulfilled: Boolean = false

    fun fulFill(warehouse: Warehouse) {
        this.warehouse = warehouse
        isFulfilled = this.warehouse.remove(product, quantity)
    }

    fun isFulfilled(): Boolean {
        return isFulfilled
    }
}