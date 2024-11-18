package com.example.testappdevandroid.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Models.Keywords
import com.example.testappdevandroid.R
import kotlinx.android.synthetic.main.item_key_words.view.*

class KeyWordsAdpater(var context: Context,var listKeywords:List<Keywords>):RecyclerView.Adapter<KeyWordsAdpater.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var nameKey = view.btn_keywords
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_key_words,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            nameKey.text = listKeywords[position].name.toString()
        }
    }

    override fun getItemCount(): Int {
        return listKeywords.size
    }
}