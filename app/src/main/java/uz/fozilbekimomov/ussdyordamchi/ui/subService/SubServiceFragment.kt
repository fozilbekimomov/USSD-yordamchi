package uz.fozilbekimomov.ussdyordamchi.ui.subService

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.fozilbekimomov.ussdyordamchi.R
import uz.fozilbekimomov.ussdyordamchi.core.list.UniversalGridItemDecoration
import uz.fozilbekimomov.ussdyordamchi.core.list.adapters.DataAdapter
import uz.fozilbekimomov.ussdyordamchi.core.models.DataState
import uz.fozilbekimomov.ussdyordamchi.core.models.Datas
import uz.fozilbekimomov.ussdyordamchi.core.utils.getSubBackGround


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 10/23/20
 * @project USSD yordamchi
 */
class SubServiceFragment : Fragment(R.layout.fragment_data), SubServiceContract.View,
    DataAdapter.OnDatasClickListener {

    private val homeVM: SubServiceVM by viewModel()

    val args: SubServiceFragmentArgs by navArgs()

    private lateinit var background:ConstraintLayout

    private val adapter = DataAdapter(this)
    lateinit var serviceList: RecyclerView


    override val subServicesObserver: Observer<DataState> = Observer(::onSubServicesObserver)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        background = view.findViewById(R.id.sub_back)

        background.setBackgroundResource(getSubBackGround(args.position))

        adapter.setCurrentColor(args.position)

        serviceList = view.findViewById(R.id.content_list)
        serviceList.adapter = adapter

        val padding =
            requireActivity().resources.getDimensionPixelSize(R.dimen.service_margin)

        serviceList.layoutManager = LinearLayoutManager(requireContext())
        serviceList.addItemDecoration(UniversalGridItemDecoration(1, padding))

        view.findViewById<AppCompatTextView>(R.id.title).text = args.title

        homeVM.subServiceLiveData.observe(viewLifecycleOwner, subServicesObserver)
        homeVM.loadSubServices(args.serviceId, args.companyId)

        when (args.companyId) {
            1 -> {
                view.findViewById<AppCompatImageView>(R.id.header)
                    .setImageResource(R.drawable.ic_uzmobile_logo)
            }
            2 -> {
                view.findViewById<AppCompatImageView>(R.id.header)
                    .setImageResource(R.drawable.mobi_uz)
            }
            3 -> {
                view.findViewById<AppCompatImageView>(R.id.header)
                    .setImageResource(R.drawable.ucell)
            }
            4 -> {
                view.findViewById<AppCompatImageView>(R.id.header)
                    .setImageResource(R.drawable.beeline)
            }
        }


    }

    override fun onSubServicesObserver(state: DataState) {
        view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE


        state.data?.let {
            adapter.setData(it as List<Datas>)
            Log.d("FFFFFF", "onSubServicesObserver: $it")
        }

    }

    override fun onServiceClick(datas: Datas) {

        try {
            val intent = Intent(Intent.ACTION_DIAL,ussdToCallableUri("tel: ${datas.activationCode}"))
//            val encodedHash = Uri.encode("#")
//            intent.data = Uri.parse("tel:" + datas.activationCode.toString() + encodedHash)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun ussdToCallableUri(ussd: String): Uri? {
        var uriString: String? = ""
        if (!ussd.startsWith("tel:")) uriString += "tel:"
        for (c in ussd.toCharArray()) {
            if (c == '#') uriString += Uri.encode("#") else uriString += c
        }
        return Uri.parse(uriString)
    }

}