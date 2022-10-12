package com.extraaedge.rocketapp.adapters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extraaedge.rocketapp.R
import com.extraaedge.rocketapp.fragments.RocktDtlsFragment
import com.extraaedge.rocketapp.models.Rockets
import com.extraaedge.rocketapp.utils.AppCommonUtils

class RocketAdapter(activity: Activity, context: Context, rocketList: ArrayList<Rockets>) : RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {

    var activity: Activity
    var context: Context
    var rocketList: ArrayList<Rockets>
    var mInflater: LayoutInflater

    init {
        this.activity = activity
        this.context = context
        this.rocketList = rocketList
        this.mInflater = LayoutInflater.from(context)
    }

    inner class RocketViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var lyt_parent: LinearLayout = itemView.findViewById(R.id.lyt_parent)
        var image_rocket: ImageView = itemView.findViewById(R.id.image_rocket)
        var tv_rockt_name: TextView = itemView.findViewById(R.id.tv_rockt_name)
        var tv_rockt_country: TextView = itemView.findViewById(R.id.tv_rockt_country)
        var tv_engine_count: TextView = itemView.findViewById(R.id.tv_engine_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val itemView = mInflater.inflate(R.layout.rocket_row, parent, false)
        return RocketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        // For rocket images
        Glide.with(context)
            .load(rocketList.get(position).flickr_image.get(0))
            .into(holder.image_rocket)

        holder.tv_rockt_name.text = rocketList.get(position).name
        holder.tv_rockt_country.text = rocketList.get(position).country
        holder.tv_engine_count.text = rocketList.get(position).engine_count

        holder.lyt_parent.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id",rocketList.get(position).id)

            val rocktDtlsFrag = RocktDtlsFragment()
            rocktDtlsFrag.arguments = bundle
            AppCommonUtils.loadFragment(activity, rocktDtlsFrag)
        }

    }

    override fun getItemCount(): Int {
        return rocketList.size
    }

}