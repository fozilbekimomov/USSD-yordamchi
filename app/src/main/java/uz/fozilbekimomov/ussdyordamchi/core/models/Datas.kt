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

@Entity(tableName = "datas")
data class Datas(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,
    @ColumnInfo(name = "name")
    var contentTitle: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "service_id")
    var serviceId: Int?,
    @ColumnInfo(name = "company_id")
    var companyId: Int?,
    @ColumnInfo(name = "activattion_code")
    var activationCode: String?
)