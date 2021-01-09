package com.ikea.technical.assignment.dto

data class PurchaseResponseDto(
        val productName: String,
        val purchaseQuantity: Int,
        val purchaseText: String,
        val orderTotalAmount: Double? = null
)