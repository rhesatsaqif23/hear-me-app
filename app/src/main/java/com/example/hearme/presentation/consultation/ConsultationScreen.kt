package com.example.hearme.presentation.consultation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.presentation.component.DoctorCard
import com.example.hearme.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationScreen(
    doctors: List<DoctorDomain>,
    navController: NavController,
    currentRoute: String = "consultation",
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chat dengan Dokter",
                        style = Typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("inbox")
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "Chat Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                )
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedRoute = currentRoute
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 80.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(doctors) { doctor ->
                DoctorCard(
                    doctor = doctor,
                    onClick = {
                        navController.navigate("doctorDetail/${doctor.did}")
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ConsultationScreenPreview() {
//    val navController = rememberNavController()
//    ConsultationScreen(navController)
//}
