package com.example.marivramchoir.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Servant
import com.example.marivramchoir.ui.listener.OnServantClick
import kotlinx.android.synthetic.main.servant_row.view.*

class ServantAdapter constructor(private val context : Context, private val servants:List<Servant>, private val onServantClick: OnServantClick) : RecyclerView.Adapter<ServantAdapter.MyServantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): MyServantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.servant_row, parent, false)
        return MyServantViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyServantViewHolder, position: Int) {
        val servant = servants[position]

        holder.servantName.text = servant.servantName
        holder.servantDate.text = servant.servantDate
        holder.servantLocation.text = servant.servantLocation

        holder.servantCard.setOnClickListener {
            onServantClick.onServantClicked(servant)
        }
    }

    override fun getItemCount(): Int {
        return servants.size
    }

    class MyServantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val servantName = itemView.servant_name_row!!
        val servantDate = itemView.servant_time_row!!
        val servantLocation = itemView.servant_location_row!!
        val servantCard = itemView.servant_row_card!!

    }
}