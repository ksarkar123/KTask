package com.extraaedge.rocketapp.repository

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.extraaedge.rocketapp.base.MyApplicationClass
import com.extraaedge.rocketapp.models.RocketDetails
import com.extraaedge.rocketapp.models.Rockets
import com.extraaedge.rocketapp.utils.AppApiUrls
import com.extraaedge.rocketapp.viewmodels.RocketViewModel
import kotlinx.coroutines.*

class RecketsRepo {

    lateinit var rocketList: ArrayList<Rockets>

    companion object {
        var mInstance: RecketsRepo? = null

        fun getInstance(): RecketsRepo {

            if (mInstance == null) {
                synchronized(this)
                {
                    mInstance = RecketsRepo()
                }
            }

            return mInstance!!
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getRockets() {
        RocketViewModel.progressbar.postValue(true)

        val getRockets = JsonArrayRequest(
            Request.Method.GET,
            AppApiUrls.getRocketsUrl(),
            null,
            {
                rocketList = ArrayList()

                GlobalScope.launch(Dispatchers.IO)
                {
                    RocketViewModel.progressbar.postValue(false)

                    for (i in 0 until it.length()) {

                        val dataObj = it.getJSONObject(i)
                        val flicker_img_list = {
                            val engineArr = dataObj.getJSONArray("flickr_images")
                            val engineList = ArrayList<String>()
                            for (j in 0 until engineArr.length()) {
                                engineList.add(engineArr.get(j).toString())
                            }
                            engineList
                        }

                        val rockets = Rockets(
                            dataObj.getString("id"),
                            dataObj.getString("name"),
                            dataObj.getString("country"),
                            dataObj.getString("stages"),
                            flicker_img_list(),
                        )

                        rocketList.add(rockets)
                    }

                    if (rocketList.size > 0) {
                        RocketViewModel.rocketListData.postValue(rocketList)
                    } else {
                        RocketViewModel.rocketListData.postValue(rocketList)
                    }
                }
            })
        {
            RocketViewModel.rocketListData.postValue(rocketList)
        }

        getRockets.setShouldCache(false)
        MyApplicationClass.getInstance().addToRequestQueue(getRockets)

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getRocketDetails(id: String) {
        RocketViewModel.progressbar.postValue(true)

        val dataUrl = "${AppApiUrls.getRocketsUrl()}/$id"
        val getRocketDtls = JsonObjectRequest(
            Request.Method.GET,
            dataUrl,
            null,
            {

                GlobalScope.launch(Dispatchers.IO)
                {
                    RocketViewModel.progressbar.postValue(false)

                    val active_status = { status: Boolean ->

                        var txt_status = ""
                        if(status)
                        {
                            txt_status = "active"
                        }
                        else
                        {
                            txt_status = "not active"
                        }

                        txt_status

                    }

                    val flicker_img_list = {
                        val engineArr = it.getJSONArray("flickr_images")
                        val engineList = ArrayList<String>()
                        for (j in 0 until engineArr.length()) {
                            engineList.add(engineArr.get(j).toString())
                        }
                        engineList
                    }

                    val height = {
                        val heightObj = it.getJSONObject("height")
                        val heightData = heightObj.getString("feet")

                        var txt_height = ""
                        if(heightData.contains("."))
                        {
                            val arr = heightData.split(".")
                            txt_height = "${arr[0]} Feet ${arr[1]} Inches"
                        }
                        else
                        {
                            txt_height = "${heightData} Feet"
                        }
                        txt_height

                    }

                    val diameter = {
                        val dmtrObj = it.getJSONObject("diameter")
                        val dmtrData = dmtrObj.getString("feet")

                        var txt_diamtr = ""
                        if(dmtrData.contains("."))
                        {
                            val arr = dmtrData.split(".")
                            txt_diamtr = "${arr[0]} Feet ${arr[1]} Inches"
                        }
                        else
                        {
                            txt_diamtr = "${dmtrData} Feet"
                        }
                        txt_diamtr
                    }

                    val rocketDetails = RocketDetails(
                        it.getString("id"),
                        it.getString("name"),
                        flicker_img_list(),
                        active_status(it.getBoolean("active")),
                        it.getString("cost_per_launch"),
                        "${it.getString("success_rate_pct")} %",
                        it.getString("description"),
                        it.getString("wikipedia"),
                        height(),
                        diameter(),
                    )

                    RocketViewModel.rocketDetailsData.postValue(rocketDetails)
                }
            })
        {
            RocketViewModel.progressbar.postValue(false)
        }

        getRocketDtls.setShouldCache(false)
        MyApplicationClass.getInstance().addToRequestQueue(getRocketDtls)

    }


}