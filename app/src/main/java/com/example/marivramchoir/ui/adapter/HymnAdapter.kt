package com.example.marivramchoir.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.ui.listener.OnHymnClickListener
import kotlinx.android.synthetic.main.hymen_row.view.*

class HymnAdapter constructor(private val context: Context,
                              private val hymns:List<Hymn>,
                              private val onHymnClickListener: OnHymnClickListener) :
    RecyclerView.Adapter<HymnAdapter.MyViewHolder>() {

    private lateinit var fade:Animation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.hymen_row,parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        fade = AnimationUtils.loadAnimation(context, R.anim.scale_anim)
        holder.card.animation = fade
        holder.checkHymen.visibility = View.GONE
        holder.hymenName.text = hymns[position].hymenName
        holder.itemView.setOnClickListener {
            onHymnClickListener.onHymenClicked(hymns[position])
        }
    }

    override fun getItemCount(): Int {
        return hymns.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var hymenName = itemView.hymen_name!!
        var card = itemView.cardview!!
        var checkHymen = itemView.hymen_check!!
    }
}