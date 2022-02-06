package com.example.marivramchoir.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.marivramchoir.R
import kotlinx.android.synthetic.main.slide_item.view.*

class SliderAdapter constructor(private val context: Context) : PagerAdapter() {

    private val slideImages = arrayListOf<Int>(R.drawable.praise_icon,R.drawable.marivram_logo,
         R.drawable.booking)

    private val slideHeadLines = arrayListOf<Int>(R.string.praise_family, R.string.app_name,
        R.string.read_add )

    private val slideDescriptions = arrayListOf<Int>(R.string.praise_family_desc, R.string.marivram_desc,
        R.string.read_add_desc)



    override fun getCount(): Int {
        return slideHeadLines.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: View, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.slide_item,container as ViewGroup, false)

        val image = view.slide_item_img
        val title = view.slide_item_heading
        val description = view.slide_item_description

        image.setImageResource(slideImages[position])
        title.setText(slideHeadLines[position])
        description.setText(slideDescriptions[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}