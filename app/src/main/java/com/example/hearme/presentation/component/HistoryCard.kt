package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.data.model.HistoryData
import com.example.hearme.ui.theme.Typography

@Composable
fun HistoryCard(
    history: HistoryData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val iconRes = when (history.level.lowercase()) {
            "ringan" -> R.drawable.stress_ringan
            "sedang" -> R.drawable.stress_sedang
            "berat" -> R.drawable.stress_berat
            else -> R.drawable.stress_ringan
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "Stress Icon",
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Stress ${history.level.replaceFirstChar { it.uppercase() }}",
                    style = Typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = history.date,
                    style = Typography.labelMedium,
                    color = Color.Gray
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Next",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryCardPreview() {
    HistoryCard(
        history = HistoryData(
            level = "Sedang",
            date = "Kam, 05 Sep 2025"
        )
    )
}
