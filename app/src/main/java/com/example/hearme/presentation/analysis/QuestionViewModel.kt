package com.example.hearme.presentation.analysis

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearme.BuildConfig
import com.example.hearme.data.model.StressResult
import com.example.hearme.data.model.UserAnswer
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

sealed class StressUiState {
    object Idle : StressUiState()
    object Loading : StressUiState()
    data class Success(val result: StressResult) : StressUiState()
    data class Error(val message: String) : StressUiState()
}

class QuestionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<StressUiState>(StressUiState.Idle)
    val uiState: StateFlow<StressUiState> = _uiState

    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    fun analyzeAnswers(answers: List<UserAnswer>) {
        viewModelScope.launch {
            _uiState.value = StressUiState.Loading
            val prompt = buildPrompt(answers)

            try {
                val rawText = safeGenerate(prompt)
                Log.d("QuestionScreen", "Respons Gemini: $rawText")

                val result = parseStressResult(rawText)
                _uiState.value = StressUiState.Success(result)

            } catch (e: TimeoutCancellationException) {
                _uiState.value = StressUiState.Error("Analisis terlalu lama, coba lagi nanti.")
                Log.e("QuestionScreen", "Timeout analisis", e)
            } catch (e: Exception) {
                _uiState.value =
                    StressUiState.Error("Gagal menganalisis jawaban: ${e.message}")
                Log.e("QuestionScreen", "Analisis gagal", e)
            }
        }
    }

    private suspend fun safeGenerate(prompt: String): String {
        var lastError: Exception? = null
        repeat(3) { attempt ->
            try {
                Log.d("QuestionScreen", "Kirim jawaban ke Gemini (attempt ${attempt + 1})")
                val response = withTimeout(20_000) {
                    model.generateContent(prompt)
                }

                val text = response.text
                Log.d("QuestionScreen", "Raw respons Gemini:\n$text")

                if (!text.isNullOrBlank()) return text
            } catch (e: Exception) {
                lastError = e
                if (e.message?.contains("overloaded", ignoreCase = true) == true) {
                    Log.w("QuestionScreen", "Model overload, retry ${attempt + 1}")
                    delay(2000)
                } else {
                    throw e
                }
            }
        }
        throw lastError ?: Exception("Gagal generate content")
    }

    private fun parseStressResult(rawText: String): StressResult {
        return try {
            val cleaned = rawText
                .replace("```json", "")
                .replace("```", "")
                .trim()

            Log.d("QuestionScreen", "Cleaned response:\n$cleaned")

            val json = JSONObject(cleaned)
            val level = json.optString("level", "tidak diketahui")

            val recommendations = mutableListOf<String>()
            val arr = json.optJSONArray("recommendations")
            if (arr != null) {
                for (i in 0 until arr.length()) {
                    recommendations.add(arr.optString(i))
                }
            }

            StressResult(
                level = level,
                recommendations = recommendations.ifEmpty {
                    listOf("Belum ada rekomendasi dari model.")
                }
            )
        } catch (e: Exception) {
            Log.e("QuestionScreen", "Gagal parse JSON, fallback pakai raw text", e)
            StressResult(
                level = "tidak diketahui",
                recommendations = listOf(rawText.take(500))
            )
        }
    }

    private fun buildPrompt(answers: List<UserAnswer>): String {
        return """
        Kamu adalah seorang teman sebaya yang peduli dan mau mendengarkan curhat. 
        Seseorang baru saja menjawab pertanyaan tentang kondisi emosinya, aktivitas sehari-hari, rutinitas, 
        dukungan sosial, dan cara dia menghadapi stres.

        Tugasmu:
        1. Dengarkan jawaban orang ini dengan empati, seolah-olah kamu benar-benar ada di sampingnya.
        2. Tentukan tingkat stres orang ini (rendah/sedang/tinggi) dengan bahasa yang sederhana.
        3. Berikan 3–5 saran kecil yang realistis, mudah dilakukan, dan relate dengan kehidupan sehari-hari 
           (misalnya: istirahat sebentar, ngobrol dengan teman, jalan kaki sebentar, journaling, atau kegiatan ringan lain).
        4. Jangan gunakan bahasa yang kaku atau menggurui. Gunakan nada hangat, ramah, dan suportif, 
           seolah kamu adalah teman yang ingin membantu.

        Data dari jawaban orang ini:
        ${
            answers.groupBy { it.section }.entries.joinToString("\n") { section ->
                "${section.key}:\n" + section.value.joinToString("\n") { "- ${it.question}: ${it.answer}" }
            }
        }

        Format jawaban WAJIB dalam JSON valid:
        {
          "level": "rendah/sedang/tinggi",
          "recommendations": [
            "Saran sederhana 1",
            "Saran sederhana 2",
            "Saran sederhana 3"
          ]
        }
        """.trimIndent()
    }
}
