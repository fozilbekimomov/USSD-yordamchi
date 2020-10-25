package uz.fozilbekimomov.ussdyordamchi.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import uz.fozilbekimomov.ussdyordamchi.core.models.DataState


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */
interface StartContract {
    interface View {
         val companiesObserver: Observer<DataState>
        fun onCompaniesObserver(state: DataState)

         val servicesObserver: Observer<DataState>
        fun onServicesObserver(state: DataState)



    }

    interface VM {
        val companiesLiveData: LiveData<DataState>
        fun loadCompanies()

        val serviceLiveData: LiveData<DataState>
        fun loadServices()



    }
}