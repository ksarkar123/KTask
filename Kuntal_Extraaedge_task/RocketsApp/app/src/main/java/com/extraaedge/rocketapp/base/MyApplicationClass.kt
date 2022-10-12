package com.extraaedge.rocketapp.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MyApplicationClass: Application() {

    companion object{

        @SuppressLint("StaticFieldLeak")
        lateinit var mInstance: MyApplicationClass
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

        @Synchronized
        fun getInstance(): MyApplicationClass {
            return mInstance
        }

        val TAG: String = MyApplicationClass::class.java
            .getSimpleName()

        fun getAppContext(): Context {
            return context!!
        }

        private var mRequestQueue: RequestQueue? = null

    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        mInstance = this

        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    fun getRequestQueue(): RequestQueue? {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        getRequestQueue()!!.add(req)
    }


}