package com.example.hearme.presentation.analysis

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.QuestionSection
import com.example.hearme.presentation.component.SwitchMethod
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.data.model.QuestionSectionData

@Composable
fun QuestionScreen(
    navController: NavController,
    currentRoute: String = "question"
) {
    val questionSections = listOf(
        QuestionSectionData(
            title = "Pembukaan & Pencairan Suasana",
            questions = listOf(
                "Bisa ceritakan sedikit tentang bagaimana hari Anda sejauh ini?",
                "Apa hal pertama yang terlintas di pikiran Anda ketika mendengar kata “tenang” atau “damai”?"
            )
        ),
        QuestionSectionData(
            title = "Aktivitas & Rutinitas",
            questions = listOf(
                "Bagaimana biasanya Anda memulai hari Anda?",
                "Seberapa sibuk hari-hari Anda dalam seminggu terakhir?",
                "Adakah aktivitas yang menurut Anda paling melelahkan akhir-akhir ini?"
            )
        ),
        QuestionSectionData(
            title = "Emosi & Perasaan",
            questions = listOf(
                "Dalam seminggu terakhir, emosi apa yang paling sering Anda rasakan?",
                "Apa hal terakhir yang membuat Anda merasa cemas atau tegang?",
                "Bagaimana tubuh Anda biasanya bereaksi saat merasa tertekan (misalnya sulit tidur, sakit kepala, atau lainnya)?"
            )
        ),
        QuestionSectionData(
            title = "Dukungan Sosial",
            questions = listOf(
                "Jika sedang menghadapi masalah, biasanya kepada siapa Anda bercerita?",
                "Menurut Anda, apakah dukungan dari orang-orang di sekitar sudah cukup membantu?"
            )
        ),
        QuestionSectionData(
            title = "Koping & Relaksasi",
            questions = listOf(
                "Apa yang biasanya Anda lakukan untuk menenangkan diri ketika stres?",
                "Apakah ada kegiatan yang membuat Anda merasa lebih lega atau bahagia akhir-akhir ini?"
            )
        ),
        QuestionSectionData(
            title = "Refleksi & Penutup",
            questions = listOf(
                "Kalau harus menggambarkan kondisi Anda sekarang dengan satu kata, kata apa yang Anda pilih?",
                "Apa harapan Anda untuk diri sendiri dalam beberapa minggu ke depan?"
            )
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SwitchMethod(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                defaultSelected = true,
                onMethodSelected = { method ->
                    if (method == "voiceRecord") {
                        navController.navigate("voiceRecord") {
                            popUpTo("analysis") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                questionSections.forEach { section ->
                    QuestionSection(
                        title = section.title,
                        questions = section.questions
                    )
                }
            }

            MainButton(
                text = "Simpan",
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    val navController = rememberNavController()
    QuestionScreen(navController)
}
