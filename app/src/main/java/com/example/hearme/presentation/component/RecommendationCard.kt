package com.example.hearme.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.data.model.RecommendationData
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.violetNormal


@Composable
fun RecommendationCard(
    recommendation: RecommendationData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = recommendation.title,
                style = Typography.titleMedium,
                color = violetNormal,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = recommendation.content,
                style = Typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun RecommendationCardPreview() {
    HearMeTheme {
        RecommendationCard(
            recommendation = RecommendationData(
                title = "Langkah Awal",
                content = "Cobalah tarik napas dalam-dalam lalu hembuskan perlahan untuk menenangkan diri. Jangan memendam perasaan sendirian, ceritakan pada teman atau orang terdekat agar beban terasa lebih ringan. Istirahat yang cukup juga penting, karena kurang tidur hanya akan memperburuk kondisi."
            )
        )
    }
}
