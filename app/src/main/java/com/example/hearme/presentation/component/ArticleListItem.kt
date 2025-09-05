package com.example.hearme.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.data.model.ArticleData
import com.example.hearme.ui.theme.HearMeTheme
import com.example.hearme.ui.theme.Typography

@Composable
fun ArticleListItem(
    article: ArticleData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = article.photo),
            contentDescription = "Foto Artikel",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = article.title,
                style = Typography.labelMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "oleh: ${article.author}",
                style = Typography.labelMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleListItemPreview() {
    HearMeTheme {
        ArticleListItem(
            article = ArticleData(
                title = "Bagaimana Dukungan Sosial Membantu Meredakan Stres dan Kecemasan",
                author = "dr. Tyas",
                date = "2025-09-05",
                content = "Konten dummy",
                photo = R.drawable.article_1
            )
        )
    }
}
