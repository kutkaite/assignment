package com.ikea.technical.assignment.repository

import com.ikea.technical.assignment.entity.ProductArticleDetail
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class ProductArticleDetailRepositoryTest {

    @Autowired
    private lateinit var productArticleDetailRepository: ProductArticleDetailRepository

    private val productArticleDetail = ProductArticleDetail(
            articleId = 1,
            quantityRequired = 2,
            requiredByProduct = "Desk",
            productPrice = 20.5
    )

    private val anotherProductArticleDetail = ProductArticleDetail(
            articleId = 2,
            quantityRequired = 5,
            requiredByProduct = "Desk",
            productPrice = 20.5
    )

    @Test
    fun `findProductByName() should return all article details associated to product`() {
        val expected = listOf(
                productArticleDetailRepository.save(productArticleDetail),
                productArticleDetailRepository.save(anotherProductArticleDetail)
        )
        val actual = productArticleDetailRepository.findProductByName(productArticleDetail.requiredByProduct)

        Assert.assertEquals(expected, actual)
    }
}