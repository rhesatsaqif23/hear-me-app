package com.example.hearme.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hearme.data.model.ArticleData
import com.example.hearme.data.model.HistoryData
import com.example.hearme.presentation.component.*
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.VioletNormal
import com.example.hearme.R

@Composable
fun DashboardScreen(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = "dashboard"

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(VioletNormal, Color.White),
                        startY = 100f,
                        endY = 700f
                    )
                )
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopBarHome(
                name = "Jane Doe",
                notificationCount = 0
            )

            GreetingCard(navController = navController)

            Spacer(modifier = Modifier.height(20.dp))

            // Riwayat terbaru
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("history") }, // navigasi ke history
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Riwayat Terbaru",
                    style = Typography.titleMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "Next"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HistoryCardSmall(
                    history = HistoryData("Ringan", "05 Sep"),
                    modifier = Modifier.weight(1f)
                )
                HistoryCardSmall(
                    history = HistoryData("Sedang", "01 Sep"),
                    modifier = Modifier.weight(1f)
                )
                HistoryCardSmall(
                    history = HistoryData("Berat", "23 Agu"),
                    modifier = Modifier.weight(1f)
                )
                HistoryCardSmall(
                    history = HistoryData("Sedang", "19 Agu"),
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            // Artikel Hari Ini
            Text(
                text = "Artikel Hari Ini",
                style = Typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            val article1 = ArticleData(
                title = "Bagaimana Dukungan Sosial Membantu Meredakan Stres dan Kecemasan",
                author = "dr. Tyas",
                date = "2025-09-05",
                content = "Lorem ipsum",
                photo = R.drawable.article_1
            )

            val article2 = ArticleData(
                title = "Hubungan Erat Antara Pola Tidur dan Kesehatan Mental",
                author = "dr. Budianto",
                date = "2025-09-05",
                content = "Lorem ipsum",
                photo = R.drawable.article_2
            )

            val article3 = ArticleData(
                title = "Kedamaian Lewat Meditasi: Cara Sederhana Mengurangi Tekanan Pikiran",
                author = "dr. Grace Sutomo",
                date = "2025-09-05",
                content = "Lorem ipsum",
                photo = R.drawable.article_3
            )

            val article4 = ArticleData(
                title = "Mengapa Berada di Ruang Terbuka Dapat Menenangkan Jiwa",
                author = "dr. Andini Putri",
                date = "2025-09-05",
                content = "Lorem ipsum",
                photo = R.drawable.article_4
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ArticleListItem(
                    article = article1,
                    onClick = {
                        navController.navigate(
                            "article/${article1.title}/${article1.author}/${article1.date}/${article1.content}/${article1.photo}"
                        )
                    }
                )

                ArticleListItem(
                    article = article2,
                    onClick = {
                        navController.navigate(
                            "article/${article2.title}/${article2.author}/${article2.date}/${article2.content}/${article2.photo}"
                        )
                    }
                )

                ArticleListItem(
                    article = article3,
                    onClick = {
                        navController.navigate(
                            "article/${article3.title}/${article3.author}/${article3.date}/${article3.content}/${article3.photo}"
                        )
                    }
                )

                ArticleListItem(
                    article = article4,
                    onClick = {
                        navController.navigate(
                            "article/${article4.title}/${article4.author}/${article4.date}/${article4.content}/${article4.photo}"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    HearMeTheme {
        val navController = rememberNavController()
        DashboardScreen(navController = navController)
    }
}
