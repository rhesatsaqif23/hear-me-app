package com.example.hearme.presentation.component

import androidx.compose.foundation.layout.*
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
import com.example.hearme.ui.theme.Typography

@Composable
fun ProfileMenu(
    icon: Int,
    value: String,
    isTindakan: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = if (isTindakan) Color.Red else Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = value,
            style = Typography.bodyMedium.copy(
                color = if (isTindakan) Color.Red else Color.Black
            ),
            modifier = Modifier.weight(1f)
        )

        if (!isTindakan) {
            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Next",
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileMenuPreview() {
    Column {
        ProfileMenu(
            icon = R.drawable.ic_profiles,
            value = "Profil",
            isTindakan = false
        )
        ProfileMenu(
            icon = R.drawable.ic_logout,
            value = "Keluar",
            isTindakan = true
        )
    }
}
