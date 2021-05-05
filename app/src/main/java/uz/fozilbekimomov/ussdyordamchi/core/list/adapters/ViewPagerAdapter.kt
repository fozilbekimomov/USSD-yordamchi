package uz.fozilbekimomov.ussdyordamchi.core.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import uz.fozilbekimomov.ussdyordamchi.R
import uz.fozilbekimomov.ussdyordamchi.core.models.Companies


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 5/5/21
 * @project USSD yordamchi
 */


class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private val data = ArrayList<Companies>()

    fun setData(data: List<Companies>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindData(company: Companies) {


            val iconCompany = itemView.findViewById<AppCompatImageView>(R.id.logo_company)
            val mottoCompany = itemView.findViewById<AppCompatTextView>(R.id.motto_company)

            mottoCompany.text = company.motto

            when (company._id) {
                1 -> {
                    iconCompany.setImageResource(R.drawable.ic_uzmobile_logo)
                }
                2 -> {
                    iconCompany.setImageResource(R.drawable.mobi_uz)
                }
                3 -> {
                    iconCompany.setImageResource(R.drawable.ucell)
                }
                4 -> {
                    iconCompany.setImageResource(R.drawable.beeline)
                }
            }
        }

    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(container.context).inflate(R.layout.item_company, container, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}