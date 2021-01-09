package com.ikea.technical.assignment.facade

import com.ikea.technical.assignment.entity.ArticleInventory
import com.ikea.technical.assignment.repository.ArticleInventoryRepository
import org.springframework.stereotype.Component

@Component
class ArticleInventoryFacade(
        private val articleInventoryRepository: ArticleInventoryRepository
) {
    fun getArticleWithMostStock(articleIds: MutableList<Int>) = articleInventoryRepository.getMostStockRequired(articleIds)

    fun findByArticleId(articleId: Int) = articleInventoryRepository.findByArticleId(articleId)

    fun findStockByArticleId(articleId: Int) = articleInventoryRepository.findStockByArticleId(articleId)

    fun save(articleDetail: ArticleInventory) = articleInventoryRepository.save(articleDetail)
}