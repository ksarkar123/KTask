package com.extraaedge.rocketapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.extraaedge.rocketapp.R
import com.extraaedge.rocketapp.adapters.RocketAdapter
import com.extraaedge.rocketapp.utils.AppWaitDialog
import com.extraaedge.rocketapp.viewmodels.RocketViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RocketListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RocketListFragment : Fragment() {

    lateinit var root : View
    lateinit var backbtn: ImageView
    lateinit var tv_modulename: TextView
    lateinit var rockets_recy: RecyclerView
    lateinit var rocketAdapter: RocketAdapter
    lateinit var rocketViewModel: RocketViewModel
    lateinit var mWaitDialog: AppWaitDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_rocket_list, container, false)
        rocketViewModel = ViewModelProvider(this).get(RocketViewModel::class.java)

        initViews()

        rocketViewModel.getRocketList()

        RocketViewModel.rocketListData.observe(viewLifecycleOwner){
            initRocktRecyclerView()
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

    fun initViews()
    {
        backbtn = requireActivity().findViewById(R.id.backbtn)
        backbtn.visibility = View.GONE
        tv_modulename = requireActivity().findViewById(R.id.tv_modulename)
        tv_modulename.setText(getString(R.string.rocket_list))
        rockets_recy = root.findViewById(R.id.rockets_recy)
        mWaitDialog = AppWaitDialog(this.requireActivity())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initRocktRecyclerView() {
        rocketAdapter = RocketAdapter(
            requireActivity(),
            requireContext(),
            RocketViewModel.rocketListData.value!!
        )

        //expertListRecyclerView.setHasFixedSize(true)
        rockets_recy.smoothScrollToPosition(0)
        rockets_recy.setLayoutManager(LinearLayoutManager(context))
        rockets_recy.setAdapter(rocketAdapter)
        rocketAdapter.notifyDataSetChanged()
    }

}