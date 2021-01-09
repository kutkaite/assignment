package com.ikea.technical.assignment.dataloader

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.ikea.technical.assignment.dto.Product
import com.ikea.technical.assignment.entity.ProductArticleDetail
import com.ikea.technical.assignment.entity.InventoryEntity
import com.ikea.technical.assignment.facade.ProductArticleDetailFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader

class ProductDataLoader(private val productArticleDetailFacade: ProductArticleDetailFacade) : DataLoader() {

    override fun mapJsonToEntity(jsonNode: JsonNode, objectMapper: ObjectMapper) {
        val product = objectMapper.readValue(jsonNode.toString(), Product::class.java)
        mapToProductArticleDetail(product)
    }

    private fun mapToProductArticleDetail(product: Product) {
        product.articleDetails.forEach {
            val articleDetail = ProductArticleDetail(
                    articleId = it.articleId,
                    quantityRequired = it.quantity,
                    requiredByProduct = product.name,
                    productPrice = product.price)
            save(articleDetail)
        }
    }

    override fun save(inventoryEntity: InventoryEntity): InventoryEntity {
        val articleDetail = inventoryEntity as ProductArticleDetail
        return productArticleDetailFacade.save(articleDetail)
    }
}