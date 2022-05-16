package aqua.graph

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import org.jetbrains.skia.FontMgr
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.paragraph.FontCollection

object FontDefaults {
    /**
     * Hacky way to obtain default system font "San Francisco"
     * for issue https://issuetracker.google.com/u/1/issues/232598252
     *
     * Should switch to [FontFamily.Default] once the issue resolved
     */
    val fontFamily: FontFamily by lazy {
        val fontCollection = FontCollection().apply { setDefaultFontManager(FontMgr.default) }
        val typeface = fontCollection.findTypefaces(arrayOf("System Font"), FontStyle.NORMAL)[0]
        typeface?.let { FontFamily(Typeface(it)) } ?: FontFamily.Default
    }
}
