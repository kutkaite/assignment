package com.ikea.technical.assignment.repository

import com.ikea.technical.assignment.entity.ProductArticleDetail
import com.ikea.technical.assignment.entity.ProductInventory
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class ProductInventoryRepositoryTest {

    @Autowired
    private lateinit var productInventoryRepository: ProductInventoryRepository

    private val productInventory = ProductInventory(
            name = "Desk",
            maxQuantityAvailable = 5,
            price = 20.5
    )

    @Test
    fun `findAvailableQuantity() should return available quantity for a given product`() {
        productInventoryRepository.save(productInventory)
        val actual = productInventoryRepository.findAvailableQuantity(productInventory.name)

        Assert.assertEquals(productInventory.maxQuantityAvailable, actual)
    }
}