// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import aqua.control.PushButton
import aqua.graph.ColorDefaults
import aqua.graph.FontDefaults

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme(
        colors = lightColors(
            primary = ColorDefaults.primary,
            primaryVariant = ColorDefaults.primary,
        ),
        shapes = Shapes(
            small = RoundedCornerShape(5.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(12.dp),
        ),
        typography = Typography(
            defaultFontFamily = FontDefaults.fontFamily,
            body1 = TextStyle(fontSize = 13.sp, lineHeight = 16.sp),
            button = TextStyle(fontSize = 13.sp, lineHeight = 16.sp),
        ),
    ) {
        Column(Modifier.padding(100.dp)) {
            PushButton(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
