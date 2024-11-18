package com.example.testappdevandroid.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappdevandroid.Models.Cast
import com.example.testappdevandroid.R
import kotlinx.android.synthetic.main.recycle_view_casts.view.*

class CastsAdpater(var context: Context, var listfilter : List<Cast>): RecyclerView.Adapter<CastsAdpater.ViewHolder>(){
    private var pathImage:String = "https://image.tmdb.org/t/p/w500"
    private var newImage:String? = null


    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var namelist = view.text_name
        var imagecast = view.image_casts
        var characterlist = view.text_char

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.recycle_view_casts,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        newImage = pathImage + listfilter[position].profilePath

            Glide
                .with(context)
                .load(newImage)
                .into(holder.imagecast)

        holder.apply {
            namelist.text = listfilter[position].name
            characterlist.text = listfilter[position].character
        }

    }

    override fun getItemCount(): Int {
        return listfilter.size
    }

}
