package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.ui.theme.Typography

@Composable
fun ChatListItem(
    doctor: DoctorDomain,
    message: String,
    time: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.doctor_placeholder),
            contentDescription = "Foto Dokter",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = doctor.dName,
                style = Typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = Typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Waktu
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = time,
                style = Typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListItemPreview() {
    val doctorDummy = DoctorDomain(
        did = "1",
        dName = "dr. Tirta, S.Psi",
        specialist = "Psikiater, Kejiwaan",
        rating = 4.5,
        experience = 10,
        clinic = "Sehat Jiwa Clinic",
        address = "Jl. Kebon Jeruk No. 12",
        city = "Jakarta Timur",
        education = listOf("S.Psi - UI", "Sp.KJ - UGM"),
        dPhoneNumber = "08123456789",
        photo = R.drawable.doctor_1
    )

    ChatListItem(
        doctor = doctorDummy,
        message = "Halo, selamat pagi",
        time = "10.15"
    )
}
