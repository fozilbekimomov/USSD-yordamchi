package uz.fozilbekimomov.ussdyordamchi.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.fozilbekimomov.ussdyordamchi.core.models.Companies
import uz.fozilbekimomov.ussdyordamchi.core.models.Datas
import uz.fozilbekimomov.ussdyordamchi.core.models.Services


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */


@Database(
    entities = [Companies::class, Datas::class, Services::class],
    exportSchema = true,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}