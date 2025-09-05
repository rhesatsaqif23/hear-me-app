package com.example.hearme.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.grey2

@Composable
fun QuestionSection(
    title: String,
    questions: List<String>,
    onAnswer: (question: String, answer: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = Typography.bodyLarge
            )
            Icon(
                painter = painterResource(id = if (expanded) R.drawable.ic_up else R.drawable.ic_down),
                contentDescription = if (expanded) "Collapse" else "Expand"
            )
        }

        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                questions.forEachIndexed { index, question ->
                    var answer by rememberSaveable { mutableStateOf("") }

                    Column {
                        Text(
                            text = "${index + 1}. $question",
                            style = Typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(
                                    width = 1.dp,
                                    color = grey2,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                        ) {
                            BasicTextField(
                                value = answer,
                                onValueChange = {
                                    answer = it
                                    onAnswer(question, it)
                                },
                                textStyle = Typography.bodyMedium,
                                modifier = Modifier.fillMaxSize(),
                                decorationBox = { innerTextField ->
                                    if (answer.isEmpty()) {
                                        Text(
                                            text = "Tulis jawaban anda",
                                            style = Typography.bodyMedium.copy(color = grey2)
                                        )
                                    }
                                    innerTextField()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun QuestionSectionPreview() {
//    QuestionSection(
//        title = "Pembukaan",
//        questions = listOf(
//            "Bisa ceritakan sedikit tentang bagaimana hari Anda sejauh ini?",
//            "Apa hal pertama yang terlintas ketika mendengar kata 'tenang' atau 'damai'?"
//        )
//    )
//}
