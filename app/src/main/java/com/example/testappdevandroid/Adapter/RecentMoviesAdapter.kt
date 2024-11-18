package com.example.testappdevandroid.Adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappdevandroid.R
import com.example.testappdevandroid.Results
import kotlinx.android.synthetic.main.item_recent.view.*

class RecentMoviesAdapter(var context:Context, var viewRecent:ArrayList<Results>):RecyclerView.Adapter<RecentMoviesAdapter.ViewHolderRecent>(){
    class ViewHolderRecent(view:View) : RecyclerView.ViewHolder(view) {
        var imageRecent = view.image_movie_recent
        var nameMovieRecent = view.movie_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecent {
        var viewRe = LayoutInflater.from(context).inflate(R.layout.item_recent,parent,false)
        return ViewHolderRecent(viewRe)
    }

    override fun onBindViewHolder(holder: ViewHolderRecent, position: Int) {
        var fullImage = "https://image.tmdb.org/t/p/w500" + viewRecent[position].posterPath

        Glide
            .with(context)
            .load(fullImage)
            .into(holder.imageRecent)
        holder.apply {
            nameMovieRecent.text = viewRecent[position].title
        }
    }

    override fun getItemCount(): Int {
        return viewRecent.size
    }

}