package com.ikea.technical.assignment.dto

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test

internal class ProductTest {
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)

    @Test
    fun `Should de-serialize Product`() {
        val json =
                """{
                    "name": "Dining Chair",
                    "price": 30,
                    "contain_articles": [
                    {
                        "art_id": "1",
                        "amount_of": "4"
                    },
                    {
                        "art_id": "2",
                        "amount_of": "8"
                    },
                    {
                        "art_id": "3",
                        "amount_of": "1"
                    }]
                }
                """.trimIndent()

        objectMapper.readValue(json, Product::class.java)
    }
}