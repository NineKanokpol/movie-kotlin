package com.example.testappdevandroid.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappdevandroid.Interface.OnClickSearchInterface
import com.example.testappdevandroid.Models.Cast
import com.example.testappdevandroid.R
import com.example.testappdevandroid.Results
import kotlinx.android.synthetic.main.item_home_fragment.view.*
import kotlinx.android.synthetic.main.recycle_search_view.view.*

class HomeAdpater(var context: Context, var viewHome: List<Cast>): RecyclerView.Adapter<HomeAdpater.ViewHolderHome>() {
    private var newImage:String? = null
    private var pathImage:String = "https://image.tmdb.org/t/p/w500"
    class ViewHolderHome(view:View) : RecyclerView.ViewHolder(view) {
        var nameMore = view.title_home
        var imageMore = view.image_list_home
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHome {
        var view = LayoutInflater.from(context).inflate(R.layout.item_home_fragment,parent,false)
        return ViewHolderHome(view)
    }

    override fun onBindViewHolder(holder: ViewHolderHome, position: Int) {
        newImage = pathImage + viewHome[position].profilePath

        if(newImage == "https://image.tmdb.org/t/p/w500null") {
            Glide
                .with(context)
                .load("https://media.istockphoto.com/vectors/male-user-icon-vector-id517998264?k=20&m=517998264&s=612x612&w=0&h=pdEwtkJlZsIoYBVeO2Bo4jJN6lxOuifgjaH8uMIaHTU=")
                .into(holder.imageMore)
        }else{
            Glide
                .with(context) //ต้องการ contect
                .load(newImage)
                .into(holder.imageMore);
        }


        holder.apply {
            nameMore?.text = viewHome[position].name.toString()
        }
    }

    override fun getItemCount(): Int {
        return viewHome.size
    }

}