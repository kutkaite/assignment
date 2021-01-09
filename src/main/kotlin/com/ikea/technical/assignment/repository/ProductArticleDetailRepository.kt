package com.ikea.technical.assignment.repository

import com.ikea.technical.assignment.entity.ProductArticleDetail
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ProductArticleDetailRepository : CrudRepository<ProductArticleDetail, Long?> {
    @Query("SELECT * FROM product_article_detail WHERE required_by_product = :productName", nativeQuery = true)
    fun findProductByName(productName: String?): Iterable<ProductArticleDetail>
}