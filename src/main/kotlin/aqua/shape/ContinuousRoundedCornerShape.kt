package aqua.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import aqua.graph.continuousRoundRect

class ContinuousRoundedCornerShape(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize
) : CornerBasedShape(topStart, topEnd, bottomEnd, bottomStart) {
    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): CornerBasedShape = ContinuousRoundedCornerShape(
        topStart, topEnd, bottomEnd, bottomStart
    )

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        if (topStart + topEnd + bottomEnd + bottomStart == 0.0f) {
            return Outline.Rectangle(size.toRect())
        }
        return Outline.Generic(Path().continuousRoundRect(size.toRect(), floatArrayOf(topStart, topEnd, bottomEnd, bottomStart).minOf { it }))
    }
}

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 * @param corner [CornerSize] to apply.
 */
fun ContinuousRoundedCornerShape(corner: CornerSize) =
    ContinuousRoundedCornerShape(corner, corner, corner, corner)

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 * @param size Size in [Dp] to apply.
 */
fun ContinuousRoundedCornerShape(size: Dp) = ContinuousRoundedCornerShape(CornerSize(size))

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 * @param size Size in pixels to apply.
 */
fun ContinuousRoundedCornerShape(size: Float) = ContinuousRoundedCornerShape(CornerSize(size))

/**
 * Creates [ContinuousRoundedCornerShape] with the same size applied for all four corners.
 * @param percent Size in percents to apply.
 */
fun ContinuousRoundedCornerShape(percent: Int) =
    ContinuousRoundedCornerShape(CornerSize(percent))
