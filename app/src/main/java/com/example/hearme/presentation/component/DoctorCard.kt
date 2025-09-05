package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import com.example.hearme.R
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.ui.theme.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.hearme.ui.theme.green

@Composable
fun DoctorCard(
    doctor: DoctorDomain,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .height(220.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = doctor.photo),
            contentDescription = "Foto Dokter",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            Column {
                Text(
                    text = doctor.dName,
                    style = Typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = doctor.specialist,
                    style = Typography.labelMedium,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "Rating",
                            tint = green,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = doctor.rating.toString(),
                            style = Typography.labelMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = doctor.city,
                        style = Typography.labelLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DoctorCardPreview() {
    val doctorDummy = DoctorDomain(
        did = "1",
        dName = "dr. Tirta, S.Psi",
        specialist = "Psikiater",
        rating = 4.5,
        experience = 5,
        clinic = "Sehat Jiwa Clinic",
        address = "Jl. Kebon Jeruk No. 12",
        city = "Jakarta Timur",
        education = listOf("S.Psi - UI", "Sp.KJ - UGM"),
        dPhoneNumber = "08123456789",
        photo = R.drawable.doctor_1
    )
    DoctorCard(
        doctor = doctorDummy,
        onClick = {}
    )
}
