package com.example.hearme.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.presentation.component.ProfileMenu
import com.example.hearme.presentation.navigation.BottomNavBar
import com.example.hearme.ui.theme.Typography

@Composable
fun ProfileScreen(
    navController: NavController,
    currentRoute: String = "profile"
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Info Pribadi
            Column {
                Text("Info Pribadi", style = Typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))

                ProfileMenu(
                    icon = R.drawable.ic_profiles,
                    value = "Profil",
                    isTindakan = false,
                    onClick = {
                        navController.navigate("editProfile")
                    }
                )
            }

            // Keamanan
            Column {
                Text("Keamanan", style = Typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))

                ProfileMenu(
                    icon = R.drawable.ic_password,
                    value = "Ubah kata sandi",
                    isTindakan = false, onClick = {
                        navController.navigate("changePassword")
                    }
                )
            }

            // Dukungan & Masukan
            Column {
                Text("Dukungan & Masukan", style = Typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))

                ProfileMenu(
                    icon = R.drawable.ic_help,
                    value = "Bantuan & Laporkan Masalah",
                    isTindakan = false
                )
                ProfileMenu(
                    icon = R.drawable.ic_star,
                    value = "Rating Aplikasi",
                    isTindakan = false
                )
            }

            // Tindakan
            Column {
                Text("Tindakan", style = Typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))

                ProfileMenu(icon = R.drawable.ic_logout, value = "Keluar", isTindakan = true)
                ProfileMenu(icon = R.drawable.ic_delete, value = "Hapus Akun", isTindakan = true)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}
