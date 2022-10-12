package com.extraaedge.rocketapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.extraaedge.rocketapp.R

class Splash : AppCompatActivity() {

    val time_in_milisecond: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({

            val intent = Intent(this@Splash,MainActivity::class.java)
            startActivity(intent)
            finish()
        },time_in_milisecond)


    }
}