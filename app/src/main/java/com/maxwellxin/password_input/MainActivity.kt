package com.maxwellxin.password_input

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : AppCompatActivity() {

    //Variable
    private var _password = MutableStateFlow(InputContent("", null, false))
    private var password = _password.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    AndroidView(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(), // Occupy the max size in the Compose UI tree
                        factory = { context ->
                            PasswordInput(context).apply {}
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    PasswordInputComponent(
                        label = "Password",
                        hint = "Enter Password",
                        inputContent = password.collectAsState().value,
                        labelColor = colorResource(android.R.color.darker_gray),
                        borderColor = colorResource(android.R.color.darker_gray),
                        onValueChange = {
                            _password.value = InputContent(
                                it,
                                password.value.error,
                                password.value.visible
                            )
                        },
                        onVisibleChange = {
                            _password.value = InputContent(
                                password.value.value,
                                password.value.error,
                                it
                            )
                        }
                    )
                }
            }
        }
    }
}