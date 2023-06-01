package com.maxwellxin.password_input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class InputContent(
    var value: String = "",
    var error: String? = null,
    var visible: Boolean = false,
)

@Composable
fun PasswordInputComponent(
    label: String?,
    hint: String,
    fontSize: TextUnit = 14.sp,
    labelColor: Color,
    borderColor: Color,
    fontFamily: FontFamily? = null,
    inputContent: InputContent,
    onValueChange: (String) -> Unit,
    onVisibleChange: (Boolean) -> Unit,
) {
    fontFamily ?: FontFamily(
        listOf(
            Font(R.font.dancing_script_bold)
        )
    )

    Column {
        if (label != null)
            Text(
                text = label,
                color = labelColor,
                fontFamily = fontFamily,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        BasicTextField(
            value = inputContent.value,
            onValueChange = onValueChange,
            maxLines = 1,
            singleLine = true,
            visualTransformation = if (inputContent.visible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle.Default.copy(
                fontSize = fontSize,
                fontFamily = fontFamily,
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (inputContent.error == null) borderColor else MaterialTheme.colors.error,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(all = 12.dp),
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (inputContent.value.isEmpty())
                            Text(
                                text = hint,
                                fontSize = fontSize,
                                fontFamily = fontFamily,
                                color = colorResource(android.R.color.darker_gray),
                            )
                        innerTextField.invoke()
                    }
                    Icon(
                        modifier = Modifier
                            .width(18.dp)
                            .clickable(onClick = {
                                onVisibleChange(!inputContent.visible)
                            }),
                        painter = if (inputContent.visible) painterResource(R.drawable.icon_eyes) else painterResource(R.drawable.icon_close_eyes),
                        contentDescription = "",
                    )
                }
            }
        )
        if (inputContent.error != null)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = inputContent.error!!,
                fontSize = 12.sp,
                color = MaterialTheme.colors.error
            )
    }
}