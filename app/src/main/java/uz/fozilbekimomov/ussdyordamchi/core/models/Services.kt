package uz.fozilbekimomov.ussdyordamchi.core.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */

@Entity(tableName = "services")
data class Services(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,
    @ColumnInfo(name = "name")
    var serviceName: String?
)