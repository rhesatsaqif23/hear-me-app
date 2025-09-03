package com.example.hearme.presentation.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.*

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SwitchMethod(
    modifier: Modifier = Modifier,
    defaultSelected: Boolean = false,
    onMethodSelected: (String) -> Unit = {}
) {
    var isQuestionnaireSelected by remember { mutableStateOf(defaultSelected) }

    val switchOffset by animateDpAsState(
        targetValue = if (isQuestionnaireSelected) 150.dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "switchOffset"
    )

    Box(
        modifier = modifier
            .width(250.dp)
            .height(45.dp)
            .background(
                color = VioletLightActive,
                shape = RoundedCornerShape(50)
            )
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .width(125.dp)
                .height(36.dp)
                .offset(x = switchOffset)
                .background(VioletLight, RoundedCornerShape(50))
        )

        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        isQuestionnaireSelected = false
                        onMethodSelected("Rekam Suara")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Rekam Suara",
                    style = Typography.bodyLarge,
                    color = if (!isQuestionnaireSelected) VioletNormalActive else VioletLight
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        isQuestionnaireSelected = true
                        onMethodSelected("Kuesioner")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Kuesioner",
                    style = Typography.bodyLarge,
                    color = if (isQuestionnaireSelected) VioletNormalActive else VioletLight
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwitchMethodPreview() {
    SwitchMethod()
}
