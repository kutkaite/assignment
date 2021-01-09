package com.ikea.technical.assignment.controller

import com.ikea.technical.assignment.dto.ProductInventoryResponseDto
import com.ikea.technical.assignment.dto.PurchaseRequest
import com.ikea.technical.assignment.dto.PurchaseResponseDto
import com.ikea.technical.assignment.entity.ProductInventory
import com.ikea.technical.assignment.service.InventoryService
import com.ikea.technical.assignment.service.PurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class InventoryController(
        @Autowired private val inventoryService: InventoryService,
        @Autowired private val purchaseService: PurchaseService
) {

    @RequestMapping(method = [RequestMethod.GET], value = ["/available-products"])
    fun getAllAvailableProducts(): ProductInventoryResponseDto {
        return inventoryService.getAllAvailableProducts()
    }

    @RequestMapping(method = [RequestMethod.POST], value = ["/purchase-product"])
    fun purchaseProduct(@RequestBody body: PurchaseRequest): PurchaseResponseDto {
        if (body.quantity <= 0) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "quantity has to be at least 1 or more")
        }
        return purchaseService.purchaseProduct(body.productName, body.quantity)
    }
}