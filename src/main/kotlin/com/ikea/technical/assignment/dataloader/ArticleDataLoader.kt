package com.ikea.technical.assignment.dataloader

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.ikea.technical.assignment.entity.ArticleInventory
import com.ikea.technical.assignment.entity.InventoryEntity
import com.ikea.technical.assignment.facade.ArticleInventoryFacade
import com.ikea.technical.assignment.repository.ArticleInventoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader

class ArticleDataLoader(
        private val articleInventoryFacade: ArticleInventoryFacade
) : DataLoader() {

    override fun mapJsonToEntity(jsonNode: JsonNode, objectMapper: ObjectMapper) {
        val article = objectMapper.readValue(jsonNode.toString(), ArticleInventory::class.java)
        save(article)
    }

    override fun save(inventoryEntity: InventoryEntity): InventoryEntity {
        val article = inventoryEntity as ArticleInventory
        return articleInventoryFacade.save(article)
    }
}