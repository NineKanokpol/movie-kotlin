package com.example.testappdevandroid.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappdevandroid.Models.Crew
import com.example.testappdevandroid.R
import kotlinx.android.synthetic.main.recycle_view_casts.view.*

class CrewsAdpater(var context:Context, var listCrews:List<Crew>):RecyclerView.Adapter<CrewsAdpater.ViewHolder>() {
    private var pathImage:String = "https://image.tmdb.org/t/p/w500"
    private var newImage:String? = null

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var nameCrew = view.text_name
        var imageCrew = view.image_casts
        var charCrew = view.text_char
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.recycle_view_casts,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        newImage = pathImage + listCrews[position].profilePath

            Glide
                .with(context)
                .load(newImage)
                .into(holder.imageCrew)


        holder.apply {
            nameCrew.text = listCrews[position].name
            charCrew.text = listCrews[position].knownForDepartment
        }
    }

    override fun getItemCount(): Int {
        return listCrews.size
    }


}