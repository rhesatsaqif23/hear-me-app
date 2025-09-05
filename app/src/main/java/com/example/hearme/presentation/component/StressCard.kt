package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.data.model.StressData
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography

@Composable
fun StressCard(
    stress: StressData,
    modifier: Modifier = Modifier
) {
    val (circleColor, iconRes) = when (stress.level.lowercase()) {
        "rendah" -> Color(0xFFFFC107) to R.drawable.stress_ringan // kuning
        "sedang" -> Color(0xFFFF9800) to R.drawable.stress_sedang // oranye
        "tinggi" -> Color(0xFFF44336) to R.drawable.stress_berat // merah
        else -> Color(0xFF9E9E9E) to R.drawable.stress_ringan
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .border(width = 4.dp, color = circleColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Stress Icon",
                    modifier = Modifier.size(45.dp)
                )
            }


            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Anda terdeteksi",
                    style = Typography.labelLarge,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Stress ${stress.level.replaceFirstChar { it.uppercase() }}",
                    style = Typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Jangan putus asa, kami hadir untuk membantu Anda",
                    style = Typography.labelLarge,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun StressCardPreview() {
    HearMeTheme {
        StressCard(
            stress = StressData(level = "tinggi")
        )
    }
}
