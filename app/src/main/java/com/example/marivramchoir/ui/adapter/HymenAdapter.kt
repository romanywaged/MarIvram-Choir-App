package com.example.marivramchoir.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.ui.listener.OnHymenClickListener
import kotlinx.android.synthetic.main.hymen_row.view.*

class HymenAdapter constructor(private val context: Context,
                               private val hymens:List<Hymen>,
                               private val onHymenClickListener: OnHymenClickListener) :
    RecyclerView.Adapter<HymenAdapter.MyViewHolder>() {

    private lateinit var fade:Animation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.hymen_row,parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        fade = AnimationUtils.loadAnimation(context, R.anim.scale_anim)
        holder.card.animation = fade
        holder.checkHymen.visibility = View.GONE
        holder.hymenName.text = hymens[position].hymenName
        holder.itemView.setOnClickListener {
            onHymenClickListener.onHymenClicked(hymens[position])
        }
    }

    override fun getItemCount(): Int {
        return hymens.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var hymenName = itemView.hymen_name!!
        var card = itemView.cardview!!
        var checkHymen = itemView.hymen_check!!
    }
}