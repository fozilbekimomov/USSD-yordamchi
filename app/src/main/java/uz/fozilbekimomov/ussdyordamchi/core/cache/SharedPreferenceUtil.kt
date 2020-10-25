package uz.fozilbekimomov.ussdyordamchi.core.cache

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.java.KoinJavaComponent.inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/21/20
 * @project iTV-v2.0
 */


class SharedPreferenceUtil(private val preference: SharedPreferences) {

    fun insertData(data: String) {
        preference.edit().putString("salom",data).apply()
    }

    fun getData(): String? {
       return preference.getString("salom","Fozilbek")
    }

}