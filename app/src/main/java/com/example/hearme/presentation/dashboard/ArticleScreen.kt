package com.example.hearme.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.presentation.component.TopBar
import com.example.hearme.ui.theme.Typography
import com.example.hearme.domain.model.ArticleDomain

@Composable
fun ArticleScreen(
    navController: NavController,
    article: ArticleDomain,
    onBackClick: () -> Unit = { navController.popBackStack() },
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // Header Image + TopBar
            Box {
                Image(
                    painter = painterResource(id = article.photo),
                    contentDescription = "Foto Artikel",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                TopBar(
                    title = "",
                    onBackClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = article.title,
                    style = Typography.titleMedium,
                    color = Color.Black
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Oleh: ${article.author}",
                        style = Typography.labelLarge,
                        color = Color.Gray
                    )
                    Text(
                        text = article.date,
                        style = Typography.labelLarge,
                        color = Color.Gray
                    )
                }

                Divider(thickness = 1.dp, color = Color.Black)

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "Penopang Mental",
                        style = Typography.titleSmall,
                        color = Color.Black
                    )
                    Text(
                        text = "Dalam menghadapi tekanan hidup sehari-hari, kehadiran teman dapat menjadi penopang penting bagi kesehatan mental. Dukungan sosial dari pertemanan yang sehat memberikan rasa aman, dimengerti, dan diterima apa adanya, sehingga seseorang tidak merasa sendirian dalam menghadapi masalah.",
                        style = Typography.bodyMedium,
                        color = Color.Black
                    )

                    Text(
                        text = "Manfaat Kebersamaan",
                        style = Typography.titleSmall,
                        color = Color.Black
                    )
                    Text(
                        text = "Berbagi cerita dengan teman sering kali membantu meredakan stres, bahkan sekadar tertawa bersama sudah cukup untuk membuat beban terasa lebih ringan. Interaksi ini mampu melepaskan hormon endorfin yang berperan menurunkan rasa cemas serta memperbaiki suasana hati. Teman juga bisa memberikan sudut pandang baru dalam melihat suatu masalah.",
                        style = Typography.bodyMedium,
                        color = Color.Black
                    )

                    Text(
                        text = "Kualitas Pertemanan",
                        style = Typography.titleSmall,
                        color = Color.Black
                    )
                    Text(
                        text = "Meski begitu, kualitas pertemanan lebih berharga daripada jumlahnya. Satu atau dua teman yang benar-benar peduli akan jauh lebih bermanfaat dibanding banyak kenalan tanpa kedekatan emosional. Menjaga komunikasi yang sehat, saling menghargai, dan memberi dukungan tulus adalah kunci agar pertemanan benar-benar memberi dampak positif bagi kesehatan mental.",
                        style = Typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    val navController = rememberNavController()
    val dummyArticle = ArticleDomain(
        title = "Bagaimana Dukungan Sosial Membantu Meredakan Stres dan Kecemasan",
        author = "dr. Tyas",
        date = "25 Agustus 2025",
        photo = R.drawable.article_1
    )
    ArticleScreen(navController, dummyArticle)
}
