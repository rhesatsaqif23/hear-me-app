package com.example.hearme.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hearme.R
import com.example.hearme.presentation.navigation.BottomNavItem
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.VioletNormal

@Composable
fun GreetingCard(
    navController: NavController,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(
                color = VioletNormal,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Bagaimana kabar Anda hari ini?",
                    style = Typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color.White)
                        .clickable { navController.navigate(BottomNavItem.Analysis.route) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Cek tingkat stress Anda",
                        style = Typography.labelLarge,
                        color = VioletNormal
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_hand),
                contentDescription = "Hand Icon",
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

//@Preview(showBackground = false)
//@Composable
//fun GreetingCardPreview() {
//    GreetingCard()
//}
