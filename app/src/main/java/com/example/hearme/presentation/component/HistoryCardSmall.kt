package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.data.model.HistoryData
import com.example.hearme.ui.theme.Typography

@Composable
fun HistoryCardSmall(
    history: HistoryData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val iconRes = when (history.level.lowercase()) {
            "ringan" -> R.drawable.stress_ringan
            "sedang" -> R.drawable.stress_sedang
            "berat" -> R.drawable.stress_berat
            else -> R.drawable.stress_ringan
        }

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "Stress Icon",
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = "Stress ${history.level.replaceFirstChar { it.uppercase() }}",
            style = Typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = history.date,
            style = Typography.labelSmall,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = false)
@Composable
fun HistoryCardSmallPreview() {
    HistoryCardSmall(
        history = HistoryData(
            level = "Ringan",
            date = "05 Sep"
        )
    )
}
