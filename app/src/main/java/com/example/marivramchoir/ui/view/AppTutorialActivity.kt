package com.example.marivramchoir.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.marivramchoir.R
import com.example.marivramchoir.ui.adapter.SliderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_app_toturial.*

@AndroidEntryPoint
class AppTutorialActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var sliderAdapter: SliderAdapter
    private var currentPage:Int = 0
    private val mDots = arrayOfNulls<TextView>(3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_toturial)

        sliderAdapter = SliderAdapter(this)

        view_pager.adapter = sliderAdapter

        addDotsIndicator(0)

        view_pager.addOnPageChangeListener(this)

        next_btn.setOnClickListener {


            if (next_btn.text == "Next")
            {
                view_pager.currentItem = currentPage + 1
            }
            else
            {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        back_btn.setOnClickListener {
            view_pager.currentItem = currentPage - 1
        }

    }

    private fun addDotsIndicator(position: Int)
    {

        dots_layout.removeAllViews()

        for (i in mDots.indices)
        {
            mDots [i] = TextView(this).apply {
                text = Html.fromHtml("&#8228;")
                textSize = 35F
                setTextColor( ContextCompat.getColor(this@AppTutorialActivity,
                    R.color.tot_back))
            }
            dots_layout.addView(mDots[i])
        }

        if (mDots.isNotEmpty())
        {
            mDots[position]!!.setTextColor(ContextCompat.getColor(this@AppTutorialActivity,
                R.color.white))
        }

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    @SuppressLint("SetTextI18n")
    override fun onPageSelected(position: Int) {
        addDotsIndicator(position)

        currentPage = position

        when (position) {
            0 -> {
                next_btn.isEnabled = true
                back_btn.isEnabled = false
                back_btn.visibility = View.INVISIBLE

                next_btn.text = "Next"
                back_btn.text = ""
            }
            mDots.size - 1 -> {
                next_btn.isEnabled = true
                back_btn.isEnabled = true
                back_btn.visibility = View.VISIBLE

                next_btn.text = "Finish"
                back_btn.text = "Back"
            }
            else -> {
                next_btn.isEnabled = true
                back_btn.isEnabled = true
                back_btn.visibility = View.VISIBLE

                next_btn.text = "Next"
                back_btn.text = "Back"
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

}