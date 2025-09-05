package com.example.hearme.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.red

@Composable
fun DetectCard(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, red),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_alert),
            contentDescription = "Alert",
            tint = red
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Tingkat stres terdeteksi",
            style = Typography.titleSmall
        )
    }
}

@Preview(showBackground = false)
@Composable
fun DetectCardPreview() {
    HearMeTheme {
        DetectCard()
    }
}
