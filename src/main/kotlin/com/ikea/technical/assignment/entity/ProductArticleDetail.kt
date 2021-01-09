package com.ikea.technical.assignment.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ProductArticleDetail(
        @JsonProperty("art_id")
        val articleId: Int,
        @JsonProperty("amount_of")
        val quantityRequired: Int,
        val requiredByProduct: String,
        val productPrice: Double
) : InventoryEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null

        constructor():this(-1, -1, "NOT_SET", 00.0)
}