package com.psybot.service.google

import com.google.cloud.language.v1beta2.AnalyzeSentimentResponse
import com.google.cloud.language.v1beta2.Document
import com.google.cloud.language.v1beta2.LanguageServiceClient
import org.springframework.stereotype.Service

@Service
class BardService() {

    private val languageServiceClient: LanguageServiceClient = LanguageServiceClient.create()

    fun process(text: String?): AnalyzeSentimentResponse {
        val document: Document = Document.newBuilder().setContent(text).build()
        val response: AnalyzeSentimentResponse = languageServiceClient.analyzeSentiment(document)
        return response
    }
}