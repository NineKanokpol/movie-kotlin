package com.example.testappdevandroid

import android.provider.BaseColumns

object DBContract {

    class RecentMovies : BaseColumns{
        companion object{
            val TABLE_NAME = "RECENTMOVIESTEST"
            val COLUMN_ID_MOVIE = "movieid"
            val COLUMN_MOVIE_NAME = "nameMovie"
            val COLUMN_IMG = "image"
            val COLUMN_TIME_STAMP = "timeStamp"
        }
    }
}