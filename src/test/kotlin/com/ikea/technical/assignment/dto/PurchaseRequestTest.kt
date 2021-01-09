package com.ikea.technical.assignment.dto

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test

internal class PurchaseRequestTest {
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)

    @Test
    fun `Should de-serialize Purchase Request`() {
        val json =
                """{
                    "productName": "Dining Chair",
                    "quantity": 1
                }
                """.trimIndent()

        objectMapper.readValue(json, PurchaseRequest::class.java)
    }
}