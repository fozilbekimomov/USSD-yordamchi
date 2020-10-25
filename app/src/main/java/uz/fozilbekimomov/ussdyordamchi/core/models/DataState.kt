package uz.fozilbekimomov.ussdyordamchi.core.models


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */


class DataState(
    val isLoading: Boolean,
    val data: List<Any>?,
    val error: String?
)