package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hearme.R
import com.example.hearme.domain.model.ConsultDomain
import com.example.hearme.domain.model.DoctorDomain
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.blue
import com.example.hearme.ui.theme.green

@Composable
fun ConsultCard(
    doctor: DoctorDomain,
    consult: ConsultDomain,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.height(90.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//        AsyncImage(
//            model = doctor.photo,
//            contentDescription = "Foto Dokter",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(90.dp)
//                .clip(RoundedCornerShape(8.dp))
//        )

            Image(
                painter = painterResource(id = R.drawable.doctor_placeholder),
                contentDescription = "Foto Dokter",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Text(
                        text = doctor.dName,
                        style = Typography.labelLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = doctor.specialist,
                        style = Typography.labelMedium,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = doctor.city,
                        style = Typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "Rating",
                                tint = green,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = doctor.rating.toString(),
                                style = Typography.labelMedium
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_work),
                                contentDescription = "Experience",
                                tint = blue,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${doctor.experience} tahun",
                                style = Typography.labelMedium
                            )
                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Konsultasi pada",
                style = Typography.labelLarge
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Tanggal",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = consult.date,
                    style = Typography.labelMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "Jam",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = consult.time,
                    style = Typography.labelMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clinic),
                    contentDescription = "Klinik",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = consult.clinic,
                    style = Typography.labelMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ConsultCardPreview() {
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
        photo = "https://i.imgur.com/mwL6QF5.jpeg"
    )

    val consultDummy = ConsultDomain(
        cid = "1",
        did = "1",
        uid = "123",
        date = "Jumat, 5 September 2025",
        time = "11.00 WIB",
        payment = "BPJS",
        clinic = "Klinik Sehat Raya, Jakarta Timur"
    )

    ConsultCard(
        doctor = doctorDummy,
        consult = consultDummy,
        onClick = {}
    )
}
