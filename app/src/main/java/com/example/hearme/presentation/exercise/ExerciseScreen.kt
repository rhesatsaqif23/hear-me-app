package com.example.hearme.presentation.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography

@Composable
fun ExerciseScreen(
    navController: NavController,
    currentRoute: String = "exercise"
) {
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
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "Rekomendasi Kegiatan",
                style = Typography.titleLarge,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(1.dp))

            Image(
                painter = painterResource(id = R.drawable.card_olahraga),
                contentDescription = "Olahraga",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("exerciseDetail")
                    }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_musik),
                    contentDescription = "Musik",
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.card_jurnal),
                    contentDescription = "Jurnal",
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_mendaki),
                    contentDescription = "Mendaki",
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.card_memasak),
                    contentDescription = "Memasak",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseScreenPreview() {
    HearMeTheme {
        ExerciseScreen(navController = rememberNavController())
    }
}
