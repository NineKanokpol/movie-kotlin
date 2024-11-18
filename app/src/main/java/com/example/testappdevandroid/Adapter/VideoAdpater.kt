package com.example.testappdevandroid.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Models.ResultsVideo
import com.example.testappdevandroid.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.card_view_recycle.view.*
import kotlinx.android.synthetic.main.item_view_video.view.*


class VideoAdpater(var context: Context,var listVideo:List<ResultsVideo>):RecyclerView.Adapter<VideoAdpater.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { //ระบุ id ว่าเป็นของอะไร
       var strVideo = view.youtube_player_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_view_video,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var positionVideo = listVideo[position]

        holder.apply {
            strVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(positionVideo.key.toString(), 0f)
                    youTubePlayer.pause()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }


}