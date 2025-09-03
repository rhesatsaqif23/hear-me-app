package com.example.hearme.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.grey2
import com.example.hearme.ui.theme.primary

@Composable
fun TimeButton(
    value: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = primary
    val unselectedColor = grey2

    val borderColor = if (isSelected) selectedColor else unselectedColor
    val textColor = if (isSelected) selectedColor else unselectedColor

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(30.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = Typography.bodySmall.copy(color = textColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TimeButtonPreview() {
    TimeButton(value = "09.00", isSelected = true, onClick = {})
}
