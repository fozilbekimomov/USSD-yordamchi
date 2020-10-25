package uz.fozilbekimomov.ussdyordamchi.core.list.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uz.fozilbekimomov.ussdyordamchi.R
import uz.fozilbekimomov.ussdyordamchi.core.models.Companies
import uz.fozilbekimomov.ussdyordamchi.core.views.SmoothViewPager


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/24/20
 * @project USSD yordamchi
 */
class CompaniesAdapter : PagerAdapter() {

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

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull any: Any) {
        val viewPager: ViewPager = container as ViewPager
        val v = any as View
        viewPager.removeView(v)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val company = data[position]

        val view =
            LayoutInflater.from(container.context).inflate(R.layout.item_company, container, false)

        val iconCompany = view.findViewById<AppCompatImageView>(R.id.logo_company)
        val mottoCompany = view.findViewById<AppCompatTextView>(R.id.motto_company)

        mottoCompany.text=company.motto

        when (company._id) {
            1->{
                iconCompany.setImageResource(R.drawable.ic_uzmobile_logo)
            }
            2->{
                iconCompany.setImageResource(R.drawable.mobi_uz)
            }
            3->{
                iconCompany.setImageResource(R.drawable.ucell)
            }
            4->{
                iconCompany.setImageResource(R.drawable.beeline)
            }
        }


        val viewPager: SmoothViewPager = container as SmoothViewPager
        viewPager.addView(view)
        return view
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any
    }


}