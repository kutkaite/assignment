package com.ikea.technical.assignment.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ikea.technical.assignment.dataloader.ArticleDataLoader
import com.ikea.technical.assignment.dataloader.ProductDataLoader
import com.ikea.technical.assignment.facade.ArticleInventoryFacade
import com.ikea.technical.assignment.facade.ProductArticleDetailFacade
import com.ikea.technical.assignment.facade.ProductInventoryFacade
import com.ikea.technical.assignment.repository.ArticleInventoryRepository
import com.ikea.technical.assignment.repository.ProductArticleDetailRepository
import com.ikea.technical.assignment.repository.ProductInventoryRepository
import com.ikea.technical.assignment.service.InventoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader

@ComponentScan
@Configuration
class InventoryConfiguration(
        @Autowired var resourceLoader: ResourceLoader
) {

    @Bean
    fun databaseInitializer(
            productInventoryRepository: ProductInventoryRepository,
            articleInventoryRepository: ArticleInventoryRepository,
            productArticleDetailRepository: ProductArticleDetailRepository
    ) = ApplicationRunner {
        val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)

        val articleInventoryFacade = ArticleInventoryFacade(articleInventoryRepository = articleInventoryRepository)
        val productInventoryFacade = ProductInventoryFacade(productInventoryRepository = productInventoryRepository)
        val productArticleDetailFacade = ProductArticleDetailFacade(productArticleDetailRepository = productArticleDetailRepository)

        val inventoryService = InventoryService(
                articleInventoryFacade = articleInventoryFacade,
                productInventoryFacade = productInventoryFacade,
                productArticleDetailFacade = productArticleDetailFacade)

        val articleDataLoader = ArticleDataLoader(articleInventoryFacade = articleInventoryFacade)
        articleDataLoader.load(dataType = "inventory", resourceLoader = resourceLoader, objectMapper = objectMapper)

        val productDataLoader = ProductDataLoader(productArticleDetailFacade = productArticleDetailFacade)
        productDataLoader.load(dataType = "products", resourceLoader = resourceLoader, objectMapper = objectMapper)

        inventoryService.updateProductInventory()
    }
}