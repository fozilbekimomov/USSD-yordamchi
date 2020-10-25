package uz.fozilbekimomov.ussdyordamchi.core.db

import androidx.room.Dao
import androidx.room.Query
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


@Dao
interface ServiceDao {

    @Query("SELECT * FROM companies")
    suspend fun getAllCompanies(): List<Companies>

    @Query("SELECT * FROM services")
    suspend fun getAllServices(): List<Services>

    @Query("SELECT * FROM datas WHERE company_id=:companyId AND service_id=:serviceId")
    suspend fun getSubServices(companyId: Int, serviceId: Int): List<Datas>

}