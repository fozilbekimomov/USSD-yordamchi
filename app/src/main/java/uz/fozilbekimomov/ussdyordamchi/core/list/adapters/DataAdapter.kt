package uz.fozilbekimomov.ussdyordamchi.core.list.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import uz.fozilbekimomov.ussdyordamchi.R
import uz.fozilbekimomov.ussdyordamchi.core.models.Datas
import uz.fozilbekimomov.ussdyordamchi.core.utils.getColor
import uz.fozilbekimomov.ussdyordamchi.core.utils.getColorString
import uz.fozilbekimomov.ussdyordamchi.core.utils.getSubItemBackground


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */
class DataAdapter(var onDatasClickListener: OnDatasClickListener?) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var data = ArrayList<Datas>()

    private var currentColor = 0

    fun setData(data: List<Datas>) {

        this.data.clear()

        this.data.addAll(data)

        notifyDataSetChanged()

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindData(d: Datas) {


            val name = itemView.findViewById<AppCompatTextView>(R.id.name)
            val desc = itemView.findViewById<AppCompatTextView>(R.id.desc)

            val div = itemView.findViewById<AppCompatImageView>(R.id.lef_div)
            val subItemBack = itemView.findViewById<ConstraintLayout>(R.id.sub_item_back)

            div.setColorFilter(Color.parseColor(getColorString(currentColor)))
            subItemBack.setBackgroundResource(getSubItemBackground(currentColor))


            name.text = d.contentTitle
            desc.text = d.description


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_service, parent, false)
        )
    }

    fun setCurrentColor(color: Int) {
        currentColor = color
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.itemView.setOnClickListener {
            onDatasClickListener?.onServiceClick(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnDatasClickListener {
        fun onServiceClick(datas: Datas)
    }

}