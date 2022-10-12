package com.extraaedge.rocketapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.extraaedge.rocketapp.R
import com.extraaedge.rocketapp.databinding.FragmentRocktDtlsBinding
import com.extraaedge.rocketapp.utils.AppWaitDialog
import com.extraaedge.rocketapp.viewmodels.RocketViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 * Use the [RocktDtlsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RocktDtlsFragment : Fragment() {

    lateinit var root: View
    lateinit var backbtn: ImageView
    lateinit var tv_modulename: TextView
    lateinit var tv_wiki_link: TextView
    lateinit var flickr_imageSlider: ImageSlider
    lateinit var binding: FragmentRocktDtlsBinding
    lateinit var rocketViewModel: RocketViewModel
    lateinit var mWaitDialog: AppWaitDialog
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rockt_dtls, container, false)
        root = binding.root
        binding.lifecycleOwner = this
        rocketViewModel = ViewModelProvider(this).get(RocketViewModel::class.java)

        id = arguments?.getString("id").toString()
        initViews()
        rocketViewModel.getRocktDtls(id)
        RocketViewModel.rocketDetailsData.observe(viewLifecycleOwner){
            initBannerSlider()
        }

        RocketViewModel.progressbar.observe(viewLifecycleOwner){

            Log.d("progresscheck", "Value is$it")
            if (it) {
                mWaitDialog.setMessage("please wait...")
                mWaitDialog.show()
            } else {
                mWaitDialog.dismiss()
            }
        }


        return root
    }

    fun initViews(){
        backbtn = requireActivity().findViewById(R.id.backbtn)
        backbtn.visibility = View.VISIBLE
        tv_modulename = requireActivity().findViewById(R.id.tv_modulename)
        tv_modulename.setText(getString(R.string.rocket_dtls))
        tv_wiki_link = root.findViewById(R.id.tv_wiki_link)
        flickr_imageSlider = root.findViewById(R.id.flickr_imageSlider)
        mWaitDialog = AppWaitDialog(this.requireActivity())

        backbtn.setOnClickListener {
            onBackPressed()
        }

        // system's back events
        root.isFocusableInTouchMode = true
        root.requestFocus()
        root.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            Log.i("Keycheck", "keyCode: $keyCode")
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                onBackPressed()
                return@OnKeyListener true
            }
            false
        })

        tv_wiki_link.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(RocketViewModel.rocketDetailsData.value!!.wikipedia))
            startActivity(browserIntent)

        }


    }

    fun initBannerSlider()
    {
        val imageList = ArrayList<SlideModel>()

        val bannerimgList = RocketViewModel.rocketDetailsData.value!!.flicker_images
        val listsize = RocketViewModel.rocketDetailsData.value!!.flicker_images.size

        for(i in 0 until listsize)
        {
            val imgUrl = RocketViewModel.rocketDetailsData.value!!.flicker_images.get(i)
            imageList.add(SlideModel(imgUrl, ScaleTypes.CENTER_CROP))
        }

        flickr_imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    fun onBackPressed(){
        val maneger: FragmentManager = requireActivity().supportFragmentManager
        maneger.beginTransaction()
            .remove(this)
            .commit()
        maneger.popBackStack()
    }


}