package com.example.hearme.presentation.consultation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hearme.R
import com.example.hearme.domain.model.ConsultDomain
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.presentation.component.MainButton
import com.example.hearme.presentation.component.TopBar
import com.example.hearme.ui.theme.Typography
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun PaymentScreen(
    navController: NavController,
    doctor: DoctorDomain,
    consult: ConsultDomain,
    onBackClick: () -> Unit = { navController.popBackStack() }
) {
    var timeLeft by remember { mutableStateOf(300) }

    // Countdown effect
    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = "Pembayaran QRIS",
                onBackClick = onBackClick,
                modifier = Modifier
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                MainButton(
                    text = "Saya Sudah Membayar",
                    onClick = {
                        navController.navigate(
                            "success/${doctor.did}/${consult.cid}/${consult.date}/${consult.time}/${consult.payment}"
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // QRIS Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.qris),
                        contentDescription = "QRIS Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Image(
                        painter = painterResource(id = R.drawable.qris_barcode),
                        contentDescription = "QRIS Barcode",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Countdown text
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Selesaikan pembayaran dalam ",
                    style = Typography.bodyLarge
                )
                Text(
                    text = formattedTime,
                    style = Typography.bodyLarge.copy(color = Color.Red)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewPaymentScreen() {
//    val navController = rememberNavController()
//    PaymentScreen(navController = navController)
//}