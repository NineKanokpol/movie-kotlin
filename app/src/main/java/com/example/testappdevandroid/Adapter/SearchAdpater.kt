package com.example.testappdevandroid.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testappdevandroid.HomeFragment
import com.example.testappdevandroid.Interface.OnClickSearchInterface
import com.example.testappdevandroid.R
import com.example.testappdevandroid.Results
import com.example.testappdevandroid.TestSearch
import kotlinx.android.synthetic.main.recycle_search_view.view.*

class  SearchAdpater(var context: TestSearch, var viewMore: List<Results>):RecyclerView.Adapter<SearchAdpater.ViewHolderDetail>() {
    private var newImage:String? = null
    private var mOnMovieListClick: OnClickSearchInterface? = null
    private var mOnClickIntoDetail:List<Results>? = null
    private var pathImage:String = "https://image.tmdb.org/t/p/w500"

    fun setOnMovieSearchClick(nOnClickSearch: OnClickSearchInterface){
        mOnMovieListClick = nOnClickSearch
    }

    class ViewHolderDetail(view: View) : RecyclerView.ViewHolder(view){
        var nameMore = view.title_search
        var imageMore = view.image_list_search
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDetail {
        var view = LayoutInflater.from(context).inflate(R.layout.recycle_search_view,parent,false)
        return ViewHolderDetail(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDetail, position: Int) {
        mOnClickIntoDetail = viewMore
        newImage = pathImage + viewMore[position].posterPath

        Glide
            .with(context) //ต้องการ contect
            .load(newImage)
            .into(holder.imageMore);


        holder.apply {
            nameMore?.text = viewMore[position].title.toString()
            itemView.setOnClickListener{
                if(mOnMovieListClick != null){
                    mOnMovieListClick?.onClickMovie(position,mOnClickIntoDetail)
                }
            }
        }
    }





    override fun getItemCount(): Int {
        return viewMore.size
    }

}
