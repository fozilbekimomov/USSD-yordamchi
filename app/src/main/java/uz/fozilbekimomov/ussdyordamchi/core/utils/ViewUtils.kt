package uz.fozilbekimomov.ussdyordamchi.core.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import uz.fozilbekimomov.ussdyordamchi.R


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */


fun Activity.setItemStatusBarColor(@ColorInt color: Int, darkStatusBarTint: Boolean) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return

    val window: Window = (window).also {
        it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        it.statusBarColor = color
    }

    val decor = window.decorView
    if (darkStatusBarTint) {
        decor.systemUiVisibility = decor.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        // We want to change tint color to white again.
        // You can also record the flags in advance so that you can turn UI back completely if
        // you have set other flags before, such as translucent or full screen.
        decor.systemUiVisibility = 0
    }
}

private val colors =
    arrayListOf(R.color.uzmobile, R.color.mobiuz, R.color.ucell, R.color.beeline)

fun getColor(position: Int): Int {
    return colors[position % colors.size]
}

private val colorsString =
    arrayListOf("#2196F3", "#E33333","#6545D7", "#FFB907")

fun getColorString(position: Int): String {
    return colorsString[position % colorsString.size]
}

private val homeBack =
    arrayListOf(R.drawable.ic_back_uzmobile, R.drawable.ic_back_mobiuz, R.drawable.ic_back_ucell, R.drawable.ic_back_beeline)

fun getHomeBackGround(position: Int): Int {
    return homeBack[position % homeBack.size]
}

private val subBack =
    arrayListOf(R.drawable.ic_sub_back_uzmobile, R.drawable.ic_sub_back_mobiuz, R.drawable.ic_sub_back_ucell, R.drawable.ic_sub_back_beeline)

fun getSubBackGround(position: Int): Int {
    return subBack[position % subBack.size]
}

private val subItemBack =
    arrayListOf(R.drawable.sub_item_back_uzmobile, R.drawable.sub_item_back_mobiuz, R.drawable.sub_item_back_ucell, R.drawable.sub_item_back_beeline)

fun getSubItemBackground(position: Int): Int {
    return subItemBack[position % subItemBack.size]
}