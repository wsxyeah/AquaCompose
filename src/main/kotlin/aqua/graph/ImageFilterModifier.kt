package aqua.graph

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.withSaveLayer
import org.jetbrains.skia.IRect
import org.jetbrains.skia.ImageFilter

/**
 * Apply [ImageFilter] into content paint
 *
 * TODO: compose multiple calls
 *
 * @param imageFilters the image filters to apply
 */
fun Modifier.imageFilter(
    vararg imageFilters: ImageFilter
) = drawWithCache {
    val paint = Paint().also {
        val frameworkPaint = it.asFrameworkPaint()
        frameworkPaint.imageFilter = imageFilters.fold(frameworkPaint.imageFilter, ImageFilter::makeCompose)
    }
    onDrawWithContent {
        drawContext.canvas.withSaveLayer(drawContext.size.toRect(), paint, ::drawContent)
    }
}

data class DropShadowParam(
    var dx: Float,
    var dy: Float,
    var sigmaX: Float,
    var sigmaY: Float,
    var color: Int,
    var crop: IRect? = null,
)

fun Modifier.dropShadow(
    vararg dropShadowParams: DropShadowParam
) = imageFilter(*dropShadowParams.map {
    ImageFilter.makeDropShadow(
        dx = it.dx,
        dy = it.dy,
        sigmaX = it.sigmaX,
        sigmaY = it.sigmaY,
        color = it.color,
        crop = it.crop
    )
}.toTypedArray())
