package uz.fozilbekimomov.ussdyordamchi.core.list.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.fozilbekimomov.ussdyordamchi.R
import uz.fozilbekimomov.ussdyordamchi.core.list.helpers.ServiceDiffUtil
import uz.fozilbekimomov.ussdyordamchi.core.models.Services
import uz.fozilbekimomov.ussdyordamchi.core.utils.getColor
import uz.fozilbekimomov.ussdyordamchi.core.utils.getColorString


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */
class ServiceAdapter(var onServiceClickListener: OnServiceClickListener?) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    private var data = ArrayList<Services>()

    private var currentColor= 0

     fun setData(data: List<Services>) {

        val diffUtil = ServiceDiffUtil(this.data, data)

        val diffResult = DiffUtil.calculateDiff(diffUtil)

        this.data.clear()

        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindData(d: Services) {


            val name = itemView.findViewById<AppCompatTextView>(R.id.name_service)
            val image = itemView.findViewById<AppCompatImageView>(R.id.icon_service)
            name.text = d.serviceName

            when (d._id) {
                1->{
                    image.setImageResource(R.drawable.ic_sim_card)
                }
                2->{
                    image.setImageResource(R.drawable.ic_internet)
                }
                3->{
                    image.setImageResource(R.drawable.ic_hashtag_1)
                }
                4->{
                    image.setImageResource(R.drawable.ic_service)
                }
                5->{
                    image.setImageResource(R.drawable.ic_sms)
                }
                6->{
                    image.setImageResource(R.drawable.ic_clock_time)
                }
            }

            image.setColorFilter(Color.parseColor(getColorString(currentColor)))
            name.setTextColor(Color.parseColor(getColorString(currentColor)))


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.itemView.setOnClickListener {
            onServiceClickListener?.onServiceClick(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setCurrentColor(color: Int) {
        currentColor = color
        notifyDataSetChanged()
    }

    interface OnServiceClickListener{
        fun onServiceClick(services: Services)
    }

}