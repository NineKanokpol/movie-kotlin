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
import com.example.testappdevandroid.Models.ResultsReviews
import com.example.testappdevandroid.R
import kotlinx.android.synthetic.main.cardview_reviews.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReviewsAdpater(var context: Context,var viewReviews:List<ResultsReviews>):RecyclerView.Adapter<ReviewsAdpater.ViewHolderReview>() {
    private var profileImage:String? = null
    private var pathImage:String = "https://image.tmdb.org/t/p/w500"
    class ViewHolderReview(view:View) : RecyclerView.ViewHolder(view) {
        var imageProfile = view.profile_image
        var nameprofile = view.name_profile
        var dateprofile = view.name_date
        var detailcomment = view.read_more_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReview {
        var viewR = LayoutInflater.from(context).inflate(R.layout.cardview_reviews,parent,false)
        return ViewHolderReview(viewR)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolderReview, position: Int) {
        profileImage = pathImage + viewReviews[position].authorDetails?.avatarPath


        Log.e("path", profileImage.toString())
        if(profileImage == "https://image.tmdb.org/t/p/w500null") {
            Glide
                .with(context)
                .load("https://cdn.pixabay.com/photo/2014/04/02/10/25/man-303792_960_720.png")
                .into(holder.imageProfile)
        }else{
            Glide
                .with(context)
                .load(profileImage)
                .into(holder.imageProfile)
        }
        holder.apply {
            nameprofile?.text = viewReviews[position].author
            detailcomment?.text = viewReviews[position].content

        }
    }
    override fun getItemCount(): Int {
        return viewReviews.size
    }

}