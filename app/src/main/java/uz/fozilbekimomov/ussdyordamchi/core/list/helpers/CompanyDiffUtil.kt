package uz.fozilbekimomov.ussdyordamchi.core.list.helpers

import androidx.recyclerview.widget.DiffUtil
import uz.fozilbekimomov.ussdyordamchi.core.models.Companies


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */

class CompanyDiffUtil(var oldData: List<Companies>, var newData: List<Companies>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition]._id == newData[newItemPosition]._id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldData[oldItemPosition].companyName == newData[newItemPosition].companyName
                && oldData[oldItemPosition]._id == newData[newItemPosition]._id
                && oldData[oldItemPosition].motto == newData[newItemPosition].motto
    }

}