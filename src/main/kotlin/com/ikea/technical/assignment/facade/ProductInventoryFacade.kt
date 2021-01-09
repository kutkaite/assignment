package com.ikea.technical.assignment.facade

import com.ikea.technical.assignment.entity.ProductInventory
import com.ikea.technical.assignment.repository.ProductInventoryRepository
import org.springframework.stereotype.Component

@Component
class ProductInventoryFacade(
        private val productInventoryRepository: ProductInventoryRepository
) {

    fun findAll() = productInventoryRepository.findAll().toList()

    fun findAvailableQuantity(productName: String) = productInventoryRepository.findAvailableQuantity(productName)

    fun save(productInventory: ProductInventory) =  productInventoryRepository.save(productInventory)
}