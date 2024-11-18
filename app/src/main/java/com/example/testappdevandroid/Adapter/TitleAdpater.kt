package com.example.testappdevandroid.Adapter

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappdevandroid.Interface.OnClickLisentnerInterface
import com.example.testappdevandroid.MyDBhelper
import com.example.testappdevandroid.R
import com.example.testappdevandroid.Results
import kotlinx.android.synthetic.main.card_view_recycle.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TitleAdpater (var context:Context, var listMovie: List<Results>):RecyclerView.Adapter<TitleAdpater.ViewHolder>(){
    private var pathImage:String = "https://image.tmdb.org/t/p/w500"
    private var newImage:String? = null
    private var mOnMovieClick:OnClickLisentnerInterface? = null //กำหนดตัวแปรในการ click card view
    private var mOnClickInto:List<Results>? = null

    fun setOnMovieClickLisener(nOnClickLisentner: OnClickLisentnerInterface){//เรียกหน้า activity ที่ทำงาน
        mOnMovieClick = nOnClickLisentner
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { //ระบุ id ว่าเป็นของอะไร
        var namem = view.movie_name
        var imagem = view.image_movie
        var ratem = view.rate_view
        var iconstar = view.start_rate
        var dateTime = view.date_movie


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.card_view_recycle,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//แสดงผลไว้ใน item ที่สร้างไว้

        mOnClickInto = listMovie
        newImage = pathImage + listMovie[position].posterPath
        Glide
            .with(context) //ต้องการ contect
            .load(newImage)
            .into(holder.imagem);
        holder.apply {
            namem?.text = listMovie[position].title.toString()
            ratem.text = listMovie[position].voteAverage.toString()
            iconstar.setColorFilter(ContextCompat.getColor(context,R.color.yellow_dark),PorterDuff.Mode.SRC_ATOP)
            val outputFormat: DateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

            val date: Date = inputFormat.parse(listMovie[position].releaseDate.toString()) as Date
            val outputText: String = outputFormat.format(date)
            dateTime?.text = outputText
        }
//        holder.namem?.text = title_movie[position].title.toString()
//        holder.ratem.text = title_movie[position].voteAverage.toString()
//        holder.iconstar.setColorFilter(ContextCompat.getColor(context,R.color.yellow_dark),PorterDuff.Mode.SRC_ATOP)//set สี icon
//        holder.dateTime?.text = title_movie[position].releaseDate.toString()

        holder.itemView.setOnClickListener {//เพื่อดูว่า click แล้วส่งอะไรไปบ้าง
            if(mOnMovieClick != null){
                mOnMovieClick?.onMovieClick(position,mOnClickInto)
            }

        }


    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

}