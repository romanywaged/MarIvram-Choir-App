package com.example.marivramchoir.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.marivramchoir.R
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.content.Intent
import android.content.SharedPreferences

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import kotlinx.android.synthetic.main.activity_splash.*


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val splashTime = 3000
    private var topAnim: Animation? = null
    private var bottomAnim:Animation? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim= AnimationUtils.loadAnimation(this,R.anim.middle_anim)
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim)

        sharedPreferences = this.getSharedPreferences("Splash", MODE_PRIVATE)
        editor =   sharedPreferences.edit()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            image_splash.animation = topAnim
            tittle_praise.animation = bottomAnim
        }

         Handler(Looper.getMainLooper()).postDelayed({
            if (!checkShared()) {
              saveShared(true)
              navigateActivity(AppTutorialActivity::class.java)
          }

          else{
              navigateActivity(LoginActivity::class.java)
           }
        }, splashTime.toLong())

    }

    private fun saveShared(Saved: Boolean) {
        editor.putBoolean("First", Saved)
        editor.apply()
    }

    private fun checkShared(): Boolean {
        return sharedPreferences.getBoolean("First", false)
    }

    private fun navigateActivity(activity : Class<*>)
    {
        val intent = Intent(this, activity)
        startActivity(intent)
        finish()
    }
}