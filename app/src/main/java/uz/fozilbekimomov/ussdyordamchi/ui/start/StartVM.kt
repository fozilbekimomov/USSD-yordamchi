package uz.fozilbekimomov.ussdyordamchi.ui.start

import androidx.lifecycle.*
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
class StartVM(private var serviceDao: ServiceDao,state: SavedStateHandle) : ViewModel(), StartContract.VM {


    private val _companiesLiveData = MutableLiveData<DataState>()
    override val companiesLiveData: LiveData<DataState>
        get() = _companiesLiveData

    companion object {
        private val CURRENT_POSITION = "current_position"
    }


    fun saveCurrentPosition(position: Int) {
        // Sets a new value for the object associated to the key.
        savedStateHandle.set(CURRENT_POSITION, position)
    }

    fun getCurrentPosition(): Int {
        // Gets the current value of the user id from the saved state handle
        return savedStateHandle.get(CURRENT_POSITION)?: 0
    }

    private val savedStateHandle = state
    override fun loadCompanies() {

        viewModelScope.launch {
            runCatching {

                emitData(true)
                serviceDao.getAllCompanies()

            }.onSuccess {

                emitData(false, it)

            }.onFailure {

                emitData(showProgress = false, error = it.message)
            }
        }

    }

    private val _serviceLiveData = MutableLiveData<DataState>()
    override val serviceLiveData: LiveData<DataState>
        get() = _serviceLiveData

    override fun loadServices() {
        viewModelScope.launch {
            runCatching {

                emitServicesData(true)
                serviceDao.getAllServices()

            }.onSuccess {

                emitServicesData(false, it)

            }.onFailure {

                emitServicesData(showProgress = false, error = it.message)
            }
        }
    }

    private fun emitData(
        showProgress: Boolean = false,
        data: List<Any>? = null,
        error: String? = null
    ) {
        _companiesLiveData.postValue(DataState(showProgress, data, error))
    }

    private fun emitServicesData(
        showProgress: Boolean = false,
        data: List<Any>? = null,
        error: String? = null
    ) {
        _serviceLiveData.postValue(DataState(showProgress, data, error))
    }

}
