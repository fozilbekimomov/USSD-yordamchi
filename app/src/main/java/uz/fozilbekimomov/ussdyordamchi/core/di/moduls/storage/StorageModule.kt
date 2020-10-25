package uz.fozilbekimomov.ussdyordamchi.core.di.moduls.storage

import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.koin.dsl.module
import uz.fozilbekimomov.ussdyordamchi.core.cache.SharedPreferenceUtil
import uz.fozilbekimomov.ussdyordamchi.core.db.AppDatabase


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */


var storageModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }

    single { SharedPreferenceUtil(get()) }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "ussd")
            .createFromAsset("database/day.db")
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().serviceDao()
    }

}

var MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Since we didn't alter the table, there's nothing else to do here.
    }
}