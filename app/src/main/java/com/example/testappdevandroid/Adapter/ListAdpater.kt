package com.example.testappdevandroid.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Interface.OnClickTagInterface
import com.example.testappdevandroid.Models.Genres
import com.example.testappdevandroid.R
import com.example.testappdevandroid.Results
import kotlinx.android.synthetic.main.recycle_view_tag.view.*


class ListAdpater(var context: Context, var listMore : List<Genres>):RecyclerView.Adapter<ListAdpater.ViewHolder>(){
    private var mOnMovieClick: OnClickTagInterface? = null
    private var click = true
    fun setOnclickTag(nOnClickLisentner : OnClickTagInterface){
        mOnMovieClick = nOnClickLisentner
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var namelist = view.btn_list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.recycle_view_tag,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            namelist.text = listMore[position].name.toString()
        }
        holder.namelist.setOnClickListener{
            if(mOnMovieClick != null){
                if(click){
                    holder.namelist.setBackgroundColor(Color.GRAY)
                    click = false
                }
                else{
                    holder.namelist.setBackgroundColor(Color.parseColor("#7177EA"))
                    click = true
                }
                mOnMovieClick?.onClickTag(click,position,listMore[position].id)
            }
        }
    }

    override fun getItemCount(): Int {
        return listMore.size
    }

}