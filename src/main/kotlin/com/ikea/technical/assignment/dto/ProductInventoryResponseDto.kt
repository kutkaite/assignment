package com.ikea.technical.assignment.dto

import com.ikea.technical.assignment.entity.ProductInventory

data class ProductInventoryResponseDto(
        val productInventory: List<ProductInventory>,
        val message: String
)