package com.ikea.technical.assignment.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test

internal class ArticleDataLoaderTest{
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)

    @Test
    fun `should ArticleInventory`() {


    }
}