package com.example.hearme.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography

@Composable
fun ExerciseStepCard(
    title: String,
    content: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
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
                text = title,
                style = Typography.titleSmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                style = Typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ExerciseStepCardPreview() {
    HearMeTheme {
        ExerciseStepCard(
            title = "Grounding & napas (1 menit)",
            content = "Duduk tegak, tarik napas 4 hitungan, tahan 2, hembuskan 6. Ulangi beberapa kali."
        )
    }
}