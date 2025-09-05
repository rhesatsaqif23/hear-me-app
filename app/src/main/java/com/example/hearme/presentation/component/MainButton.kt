package com.example.hearme.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.violetNormal

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = violetNormal
        ),
        shape = RoundedCornerShape(50.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = Typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
fun MainButtonPreview() {
    HearMeTheme {
        MainButton(
            text = "Simpan",
            onClick = {}
        )
    }
}
