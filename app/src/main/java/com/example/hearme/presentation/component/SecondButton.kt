package com.example.hearme.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.violetNormal

@Composable
fun SecondButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(3.dp, violetNormal),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = violetNormal
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = Typography.titleSmall.copy(
                color = violetNormal
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
fun SecondButtonPreview() {
    HearMeTheme {
        SecondButton(
            text = "Konsultasikan",
            onClick = {}
        )
    }
}
