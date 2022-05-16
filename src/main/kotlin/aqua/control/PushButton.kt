package aqua.control

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import aqua.graph.DropShadowParam
import aqua.graph.dropShadow
import aqua.shape.ContinuousRoundedCornerShape

@Composable
fun PushButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = ContinuousRoundedCornerShape(5.dp),
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = PushButtonDefaults.ContentPadding,
    primary: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val contentColor by colors.contentColor(enabled)
    val elv = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp
    val absoluteElevation = LocalAbsoluteElevation.current + elv

    val backgroundColor = colors.backgroundColor(enabled).value

    // TODO extract Surface
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(
            modifier
                .dropShadow(
                    DropShadowParam(0f, 0.5f, 3f, 3f, backgroundColor.copy(alpha = 0.12f).toArgb()),
                    DropShadowParam(0f, 1f, 2f, 2f, backgroundColor.copy(alpha = 0.12f).toArgb()),
                    DropShadowParam(0f, 0.5f, 1f, 1f, backgroundColor.copy(alpha = 0.24f).toArgb()),
                )
                .background(colors.backgroundColor(enabled).value, shape)
                .background(
                    brush = PushButtonDefaults.gradientBrush,
                    alpha = PushButtonDefaults.gradientAlpha,
                    shape = shape,
                )
                .then(if (border != null) Modifier.border(border, shape) else Modifier)
                .clip(shape)
                .then(
                    Modifier.clickable(
                        enabled = enabled,
                        onClick = onClick,
                    )
                ),
        ) {
            CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
                ProvideTextStyle(
                    value = MaterialTheme.typography.button
                ) {
                    Row(
                        Modifier.padding(contentPadding),
                        content = content
                    )
                }
            }
        }
    }
}

object PushButtonDefaults {
    private val ButtonHorizontalPadding = 8.dp
    private val ButtonVerticalPadding = 2.dp

    val ContentPadding = PaddingValues(
        start = ButtonHorizontalPadding,
        top = ButtonVerticalPadding,
        end = ButtonHorizontalPadding,
        bottom = ButtonVerticalPadding
    )

    val gradientBrush = Brush.verticalGradient(
        0f to Color(0xFFFFFFFF),
        1f to Color(0x00FFFFFF),
    )
    val gradientAlpha = 0.17f
}