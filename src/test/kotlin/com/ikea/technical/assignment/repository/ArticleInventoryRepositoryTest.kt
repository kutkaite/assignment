package com.ikea.technical.assignment.repository

import com.ikea.technical.assignment.entity.ArticleInventory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class ArticleInventoryRepositoryTest {

    @Autowired
    private lateinit var articleInventoryRepository: ArticleInventoryRepository

    private val articleInventory = ArticleInventory(
            articleId = 1,
            name = "leg",
            stock = 5
    )

    private val anotherArticleInventory = ArticleInventory(
            articleId = 2,
            name = "table top",
            stock = 20
    )

    @Before
    fun setUp() {
        articleInventoryRepository.save(articleInventory)
        articleInventoryRepository.save(anotherArticleInventory)
    }

    @Test
    fun `findByArticleId() should return correct article`() {
        val expected = articleInventoryRepository.save(articleInventory)
        val actual = articleInventoryRepository.findByArticleId(articleInventory.articleId)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `findStockByArticleId() should return correct stock amount for article id`() {
        val actual = articleInventoryRepository.findStockByArticleId(articleInventory.articleId)

        Assert.assertEquals(articleInventory.stock, actual)
    }

    @Test
    fun `getMostStockRequired() should return the article with most stock`() {
        val actual = articleInventoryRepository.getMostStockRequired(
                mutableListOf(articleInventory.articleId, anotherArticleInventory.articleId))

        Assert.assertEquals(anotherArticleInventory.stock, actual)
    }
}