package uz.fozilbekimomov.ussdyordamchi.ui.subService

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.fozilbekimomov.ussdyordamchi.core.db.ServiceDao
import uz.fozilbekimomov.ussdyordamchi.core.models.DataState


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */
class SubServiceVM(private var serviceDao: ServiceDao) : ViewModel(), SubServiceContract.VM {

    private val _subServiceLiveData = MutableLiveData<DataState>()
    override val subServiceLiveData: LiveData<DataState>
        get() = _subServiceLiveData

    override fun loadSubServices(serviceId: Int, company: Int) {
        viewModelScope.launch {
            runCatching {

                emitData(true)
                serviceDao.getSubServices(company, serviceId)

            }.onSuccess {

                emitData(false, it)

            }.onFailure {

                emitData(showProgress = false, error = it.message)
            }
        }
    }

    private fun emitData(
        showProgress: Boolean = false,
        data: List<Any>? = null,
        error: String? = null
    ) {
        _subServiceLiveData.postValue(DataState(showProgress, data, error))
    }

}


