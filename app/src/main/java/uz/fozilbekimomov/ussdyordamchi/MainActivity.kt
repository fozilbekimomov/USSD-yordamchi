package uz.fozilbekimomov.ussdyordamchi

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.fozilbekimomov.ussdyordamchi.core.utils.setItemStatusBarColor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setItemStatusBarColor(Color.parseColor("#596CF1"), false)
    }
}