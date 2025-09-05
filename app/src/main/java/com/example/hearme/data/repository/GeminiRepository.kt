package com.example.hearme.data.repository

import com.example.hearme.data.model.StressResult
import com.google.ai.client.generativeai.GenerativeModel

class GeminiRepository(private val model: GenerativeModel) {

    suspend fun analyzeAnswers(prompt: String): StressResult {
        val response = model.generateContent(prompt)
        val text = response.text ?: "Tidak ada respons"

        return parseGeminiResponse(text)
    }

    private fun parseGeminiResponse(text: String): StressResult {
        val level = when {
            text.contains("rendah", true) -> "rendah"
            text.contains("sedang", true) -> "sedang"
            text.contains("tinggi", true) -> "tinggi"
            else -> "tidak diketahui"
        }
        val recommendation = text.split("\n")
            .filter { it.isNotBlank() && !it.contains(level, true) }

        return StressResult(level = level, recommendations = recommendation)
    }
}
