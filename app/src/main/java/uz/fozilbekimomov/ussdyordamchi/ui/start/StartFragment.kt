package uz.fozilbekimomov.ussdyordamchi.ui.start

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.rd.PageIndicatorView
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.fozilbekimomov.ussdyordamchi.BuildConfig
import uz.fozilbekimomov.ussdyordamchi.R
import uz.fozilbekimomov.ussdyordamchi.core.list.UniversalGridItemDecoration
import uz.fozilbekimomov.ussdyordamchi.core.list.adapters.ServiceAdapter
import uz.fozilbekimomov.ussdyordamchi.core.list.adapters.ViewPagerAdapter
import uz.fozilbekimomov.ussdyordamchi.core.models.Companies
import uz.fozilbekimomov.ussdyordamchi.core.models.DataState
import uz.fozilbekimomov.ussdyordamchi.core.models.Services
import uz.fozilbekimomov.ussdyordamchi.core.utils.getColorString
import uz.fozilbekimomov.ussdyordamchi.core.utils.getHomeBackGround
import uz.fozilbekimomov.ussdyordamchi.core.utils.setItemStatusBarColor


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */
class StartFragment : Fragment(R.layout.fragment_start), StartContract.View,
    ServiceAdapter.OnServiceClickListener {

    private var company: Companies? = null
    private var data: List<Companies>? = null
    private val homeVM: StartVM by viewModel()

    private lateinit var homeBackground: ConstraintLayout

    private var currentColorPosition = 0

    //    private val companiesAdapter = CompaniesAdapter()
    private val serviceAdapter = ServiceAdapter(this)
//    lateinit var companyList: SmoothViewPager

    private val companyAdapter = ViewPagerAdapter()

    lateinit var companyList: ViewPager2
    lateinit var serviceList: RecyclerView

    lateinit var pageIndicatorView: PageIndicatorView

    lateinit var shareApp: AppCompatImageButton
    lateinit var openTelegram: AppCompatImageButton
    lateinit var openInstagram: AppCompatImageButton
    lateinit var openMessenger: AppCompatImageButton


    override val companiesObserver: Observer<DataState> = Observer(::onCompaniesObserver)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setItemStatusBarColor(
            Color.parseColor(getColorString(currentColorPosition)),
            false
        )

        companyList = view.findViewById(R.id.company_list)

        homeBackground = view.findViewById(R.id.homeBack)

        shareApp = view.findViewById(R.id.share)
        openTelegram = view.findViewById(R.id.telegram)
        openInstagram = view.findViewById(R.id.instagram)
        openMessenger = view.findViewById(R.id.message)
        pageIndicatorView = view.findViewById(R.id.pageIndicatorView)



        serviceList = view.findViewById(R.id.service_list)
        serviceList.adapter = serviceAdapter

        val padding =
            requireActivity().resources.getDimensionPixelSize(R.dimen.service_margin)

        serviceList.layoutManager = GridLayoutManager(requireContext(), 2)
        serviceList.addItemDecoration(UniversalGridItemDecoration(2, padding))

        companyList.clipToPadding = false
        companyList.clipChildren = false

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            companyList.clipToOutline = false
        }

        homeVM.companiesLiveData.observe(viewLifecycleOwner, companiesObserver)
        homeVM.serviceLiveData.observe(viewLifecycleOwner, servicesObserver)
        homeVM.loadCompanies()
        homeVM.loadServices()

        loadListeners()

    }

    private fun loadListeners() {


        shareApp.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                var shareMessage = "\nПозвольте мне рекомендовать вам это приложение\n\n"
                shareMessage =
                    shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Выбери один"))
            } catch (e: Exception) {
                Toast.makeText(context, "Not working share app", Toast.LENGTH_SHORT).show()
            }
        }


        openTelegram.setOnClickListener {
            try {
                val telegram = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("tg://resolve?domain=" + "YANGIYOLda")
                )
                startActivity(telegram)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "Iltimos telegram dasturini o'rnating",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        setViewPagerSettings()


    }

    private fun setViewPagerSettings() {

        companyList.apply {
            clipToPadding = false   // allow full width shown with padding
            clipChildren = false    // allow left/right item is not clipped
            offscreenPageLimit = 2  // make sure left/right item is rendered
        }

// increase this offset to show more of left/right
        val offsetPx = 120
        companyList.setPadding(0, 0, offsetPx, 0)

// increase this offset to increase distance between 2 items
        val pageMarginPx = 0
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        companyList.setPageTransformer(marginTransformer)

    }

    override fun onCompaniesObserver(state: DataState) {
        view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.data?.let {
            companyAdapter.setData(it as List<Companies>)
            pageIndicatorView.count = companyAdapter.itemCount
            this.data = it
            companyList.adapter = companyAdapter
            company = it.firstOrNull()
        }

        state.error?.let {
        }

    }

    override val servicesObserver: Observer<DataState> = Observer(::onServicesObserver)

    override fun onServicesObserver(state: DataState) {
        view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.data?.let {
            serviceAdapter.setData(it as List<Services>)

        }

        state.error?.let {
        }
    }

    val callback = PageChangeCallback()

    override fun onResume() {
        super.onResume()

        companyList.visibility=View.INVISIBLE

        val position = homeVM.getCurrentPosition()
        Log.d("StartFragment", "onResume: $position")

        Handler(Looper.myLooper()!!).postDelayed({
            companyList.setCurrentItem(position, false)
            serviceAdapter.setCurrentColor(position)

            homeBackground.setBackgroundResource(getHomeBackGround(position))
            requireActivity().setItemStatusBarColor(
                Color.parseColor(getColorString(position)),
                false
            )

            companyList.visibility=View.VISIBLE

        }, 200)

        companyList.registerOnPageChangeCallback(callback)
    }


    override fun onStop() {
        super.onStop()
        companyList.unregisterOnPageChangeCallback(callback)

    }

    override fun onServiceClick(services: Services) {

        company?.let {
            val action = StartFragmentDirections.actionStartFragmentToSubServiceFragment(
                services._id,
                it._id,
                services.serviceName!!,
                currentColorPosition
            )
            findNavController().navigate(action)
        }
    }

    inner class PageChangeCallback : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)


            homeVM.saveCurrentPosition(position)

            currentColorPosition = position
            homeBackground.setBackgroundResource(getHomeBackGround(position))
            requireActivity().setItemStatusBarColor(
                Color.parseColor(getColorString(position)),
                false
            )

            serviceAdapter.setCurrentColor(position)

            data?.let {
                this@StartFragment.company = it[position]
            }

            pageIndicatorView.setSelected(position)

        }
    }

}