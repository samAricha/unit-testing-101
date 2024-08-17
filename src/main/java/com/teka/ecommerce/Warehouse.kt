package com.teka.ecommerce

class Warehouse {
    private val warehouse: HashMap<String, Int> = hashMapOf()
    fun add(product: String, quantity: Int) {
        warehouse[product] = quantity
    }

    fun remove(product: String, quantity: Int): Boolean {
        //if product is not in warehouse
        if (warehouse.contains(product).not()) {
            println("Product not found in warehouse")
            return false
        }

        //if the product quantity is not zero in the warehouse
        if (warehouse[product] == 0) {
            println("No items for this product in the warehouse")
            return false
        }

        //if the quantity in the warehouse is less than the quantity required
        if (warehouse[product]!! < quantity) {
            println("Not enough items in the warehouse")
            return false
        }

        //deduct requested quantity and update the hash-map value
        val currentQty = warehouse[product]
        val newQty = currentQty!!.minus(quantity)
        warehouse[product] = newQty
        return true
    }

    fun getInventory(product: String): Int? {
        return warehouse.get(product)
    }
}