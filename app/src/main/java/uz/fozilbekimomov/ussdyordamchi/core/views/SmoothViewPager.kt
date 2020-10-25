package uz.fozilbekimomov.ussdyordamchi.core.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import java.lang.Math.abs


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */
class SmoothViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?
) : ViewPager(context, attrs) {


    var smallerScale = 0f
    var startOffset = 0f
    var baseElevation = 0f
    var raisingElevation = 0f


    init {
        overScrollMode = OVER_SCROLL_NEVER
    }

    fun setParameters(
        smallerScale: Float,
        startOffset: Float,
        baseElevation: Float,
        raisingElevation: Float
    ) {
        this.smallerScale = smallerScale
        this.startOffset = startOffset
        this.raisingElevation = raisingElevation
        setPageTransformer(
            true,
            SmoothPageTransformer()
        )
    }

    fun setPageLimit(offscreenPageLimit: Int) {
        setPageTransformer(
            true,
            SliderTransformer(offscreenPageLimit)
        )
    }

    class SliderTransformer(private val offscreenPageLimit: Int) : ViewPager.PageTransformer {

        companion object {

            private const val DEFAULT_TRANSLATION_X = .0f
            private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

            private const val SCALE_FACTOR = .14f
            private const val DEFAULT_SCALE = 1f

            private const val ALPHA_FACTOR = .3f
            private const val DEFAULT_ALPHA = 1f

        }

        override fun transformPage(page: View, position: Float) {

            page.apply {

                ViewCompat.setElevation(page, -abs(position))

                val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
                val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

                when {
                    position <= 0f -> {
                        translationX = DEFAULT_TRANSLATION_X
                        scaleX = DEFAULT_SCALE
                        scaleY = DEFAULT_SCALE
                        alpha = DEFAULT_ALPHA + position
                    }
                    position <= offscreenPageLimit - 1 -> {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                        translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                        alpha = alphaFactor
                    }
                    else -> {
                        translationX = DEFAULT_TRANSLATION_X
                        scaleX = DEFAULT_SCALE
                        scaleY = DEFAULT_SCALE
                        alpha = DEFAULT_ALPHA
                    }
                }
            }
        }
    }

    private inner class SmoothPageTransformer : ViewPager.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val absPosition = Math.abs(position - startOffset)

            if (absPosition >= 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    page.elevation = baseElevation
                }
                page.scaleY = smallerScale
            } else {
                // This will be during transformation
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    page.elevation = (1 - absPosition) * raisingElevation + baseElevation
                }
                page.scaleY = (smallerScale - 1) * absPosition + 1
            }
        }
    }

}