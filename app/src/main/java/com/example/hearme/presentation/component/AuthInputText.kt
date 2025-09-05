package com.example.hearme.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hearme.ui.theme.Typography
import com.example.hearme.ui.theme.grey2
import com.example.hearme.R

@Composable
fun AuthInputText(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    icon: Int,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = grey2,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = grey2,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 12.dp)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = Typography.bodyMedium.copy(color = Color.Black),
                visualTransformation = if (isPassword && !passwordVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = Typography.bodyMedium.copy(color = grey2)
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier.weight(1f)
            )

            // Password toggle icon
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = grey2
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthInputTextPreview() {
    Column(Modifier.padding(16.dp)) {
        AuthInputText(
            value = "",
            onValueChange = {},
            placeholder = "Email",
            icon = R.drawable.ic_email
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthInputText(
            value = "",
            onValueChange = {},
            placeholder = "Password",
            icon = R.drawable.ic_password,
            isPassword = true
        )
    }
}

