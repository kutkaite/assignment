package com.ikea.technical.assignment.entity

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.Exception

internal class ArticleInventoryTest {
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)

    @Test
    fun `Should de-serialize ArticleInventory`() {
        val json =
                """{
                    "art_id": "1",
                    "name": "leg",
                    "stock": 30
                }
                """.trimIndent()

        objectMapper.readValue(json, ArticleInventory::class.java)
    }

    @Test
    fun `Should fail de-serialize ArticleInventory if any fields missing`() {
        val json =
                """{
                    "art_id": "1",
                    "stock": 30
                }
                """.trimIndent()


        val exception: Exception = assertThrows(ValueInstantiationException::class.java) {
            objectMapper.readValue(json, ArticleInventory::class.java)
        }

        val expectedMessage = "Cannot construct instance of `com.ikea.technical.assignment.entity.ArticleInventory`, " +
                "problem: Parameter specified as non-null is null: " +
                "method com.ikea.technical.assignment.entity.ArticleInventory.<init>, parameter name\n" +
                " at [Source: (String)\"{\n" +
                "                    \"art_id\": \"1\",\n" +
                "                    \"stock\": 30\n" +
                "                }\"; line: 4, column: 17]"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }
}