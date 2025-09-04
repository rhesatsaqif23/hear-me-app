package com.example.hearme.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.R
import com.example.hearme.ui.theme.VioletDark
import com.example.hearme.ui.theme.VioletLightActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class RecorderState {
    Idle, Recording, Finished
}

@Composable
fun VoiceRecorder(
    state: RecorderState,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    val rippleCount = 3
    val ripples = remember { List(rippleCount) { Animatable(0f) } }

    LaunchedEffect(state) {
        if (state == RecorderState.Recording) {
            ripples.forEachIndexed { index, animatable ->
                launch {
                    delay(index * 800L)
                    while (state == RecorderState.Recording) {
                        animatable.snapTo(0f)
                        animatable.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(durationMillis = 2400, easing = LinearEasing)
                        )
                    }
                }
            }
        } else {
            ripples.forEach { it.snapTo(0f) }
        }
    }

    Box(
        modifier = Modifier.size(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(CircleShape)
                .background(VioletLightActive.copy(alpha = 0.4f))
        )

        if (state == RecorderState.Recording) {
            ripples.forEach { anim ->
                val progress = anim.value
                val size = (220 + progress * 120).dp
                val alpha = 1f - progress
                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(VioletLightActive.copy(alpha = alpha))
                )
            }
        }

        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(VioletDark)
                .clickable {
                    when (state) {
                        RecorderState.Idle -> onStart()
                        RecorderState.Recording -> onStop()
                        RecorderState.Finished -> onStart()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            val icon = when (state) {
                RecorderState.Recording -> R.drawable.ic_mic_on
                else -> R.drawable.ic_mic
            }
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Mic Button",
                modifier = Modifier.size(90.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoiceRecorderPreviewIdle() {
    VoiceRecorder(
        state = RecorderState.Idle,
        onStart = {},
        onStop = {}
    )
}

@Preview(showBackground = true)
@Composable
fun VoiceRecorderPreviewRecording() {
    VoiceRecorder(
        state = RecorderState.Recording,
        onStart = {},
        onStop = {}
    )
}

@Preview(showBackground = true)
@Composable
fun VoiceRecorderPreviewFinished() {
    VoiceRecorder(
        state = RecorderState.Finished,
        onStart = {},
        onStop = {}
    )
}
