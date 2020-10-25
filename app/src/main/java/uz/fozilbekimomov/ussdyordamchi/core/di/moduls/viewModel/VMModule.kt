package uz.fozilbekimomov.ussdyordamchi.core.di.moduls.viewModel

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.fozilbekimomov.ussdyordamchi.core.db.ServiceDao
import uz.fozilbekimomov.ussdyordamchi.ui.start.StartVM
import uz.fozilbekimomov.ussdyordamchi.ui.subService.SubServiceVM
import kotlin.math.sin


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */

var vmModule = module {

    viewModel {
        StartVM(get() as ServiceDao,get())
    }

    single {
        SavedStateHandle()
    }

    viewModel {
        SubServiceVM(get() as ServiceDao)
    }
}