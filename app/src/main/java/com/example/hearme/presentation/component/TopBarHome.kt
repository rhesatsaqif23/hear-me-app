package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.ui.theme.Typography

@Composable
fun TopBarHome(
    name: String,
    modifier: Modifier = Modifier,
    notificationCount: Int = 0
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.photo_profile),
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Halo,",
                    style = Typography.bodyLarge,
                    color = Color.White
                )
                Text(
                    text = name,
                    style = Typography.bodyLarge,
                    color = Color.White
                )
            }
        }

        BadgedBox(
            badge = {
                if (notificationCount > 0) {
                    Badge(
                        containerColor = Color.Red
                    ) {
                        Text(
                            text = if (notificationCount > 99) "99+" else notificationCount.toString(),
                            color = Color.White,
                            style = Typography.labelSmall
                        )
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notif),
                contentDescription = "Notifikasi",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun TopBarHomePreview() {
    TopBarHome(
        name = "Shevia Neyliana",
        notificationCount = 100
    )
}
