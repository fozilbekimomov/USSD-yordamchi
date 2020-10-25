package uz.fozilbekimomov.ussdyordamchi.ui.subService

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
interface SubServiceContract {
    interface View {
        val subServicesObserver: Observer<DataState>
        fun onSubServicesObserver(state: DataState)
    }

    interface VM {
        val subServiceLiveData: LiveData<DataState>
        fun loadSubServices(serviceId: Int, company: Int)
    }
}