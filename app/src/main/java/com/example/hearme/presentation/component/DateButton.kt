package com.example.hearme.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.grey1
import com.example.hearme.ui.theme.violetLightActive

@Composable
fun DateButton(
    value: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) violetLightActive else grey1
    val textColor = Color.Black

    Box(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = Typography.labelMedium.copy(color = textColor),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DateButtonPreview() {
    androidx.compose.foundation.layout.Row {
        DateButton(value = "Hari\nini", isSelected = false, onClick = {})
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(8.dp))
        DateButton(value = "Jum\n5\nSept", isSelected = true, onClick = {})
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(8.dp))
        DateButton(value = "Sab\n6\nSept", isSelected = false, onClick = {})
    }
}
