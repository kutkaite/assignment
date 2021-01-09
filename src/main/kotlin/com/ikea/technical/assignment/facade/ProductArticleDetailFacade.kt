package com.ikea.technical.assignment.facade

import com.ikea.technical.assignment.entity.ProductArticleDetail
import com.ikea.technical.assignment.repository.ProductArticleDetailRepository
import org.springframework.stereotype.Component

@Component
class ProductArticleDetailFacade(
        private val productArticleDetailRepository: ProductArticleDetailRepository
) {

    fun findAll(): MutableIterable<ProductArticleDetail> = productArticleDetailRepository.findAll()

    fun getProductByName(productName: String) = productArticleDetailRepository.findProductByName(productName).toList()

    fun save(productArticleDetail: ProductArticleDetail) = productArticleDetailRepository.save(productArticleDetail)
}