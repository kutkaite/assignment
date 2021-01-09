package com.ikea.technical.assignment.service

import com.ikea.technical.assignment.dto.PurchaseResponseDto
import com.ikea.technical.assignment.entity.ArticleInventory
import com.ikea.technical.assignment.facade.ArticleInventoryFacade
import com.ikea.technical.assignment.facade.ProductArticleDetailFacade
import com.ikea.technical.assignment.facade.ProductInventoryFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        val articleInventoryFacade: ArticleInventoryFacade,
        val productInventoryFacade: ProductInventoryFacade,
        val productArticleDetailFacade: ProductArticleDetailFacade,
        val inventoryService: InventoryService
) {
    fun purchaseProduct(productName: String, productQuantity: Int): PurchaseResponseDto {
        val productArticleDetails = productArticleDetailFacade.getProductByName(productName)

        if (productArticleDetails.isNullOrEmpty()) {
            return PurchaseResponseDto(
                    productName,
                    productQuantity,
                    "There was a problem with your purchase! There is no such product."
            )
        }

        val maxAvailableQuantity = productInventoryFacade.findAvailableQuantity(productName)!!
        if (maxAvailableQuantity < productQuantity) {
            return PurchaseResponseDto(
                    productName,
                    productQuantity,
                    "There was a problem with your purchase! Not enough stock for this product."
            )
        }

        var productPrice = 0.0

        productArticleDetails.forEach {
            val articleId = it.articleId
            val articleInventory = articleInventoryFacade.findByArticleId(articleId)
            val currentStock = articleInventory.stock
            val requiredArticleQuantity = it.quantityRequired
            productPrice = it.productPrice

            val newStockAmount = currentStock - (productQuantity * requiredArticleQuantity)

            articleInventoryFacade.save(
                    ArticleInventory(
                            articleId = articleInventory.articleId,
                            name = articleInventory.name,
                            stock = newStockAmount
                    )
            )
            LOG.info("Article inventory was updated with the new stock amount")
            inventoryService.updateProductInventory()
        }

        val totalOrderAmount = productPrice * productQuantity
        return PurchaseResponseDto(productName, productQuantity, "Thanks for purchasing!", totalOrderAmount)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(PurchaseService::class.java)
    }
}