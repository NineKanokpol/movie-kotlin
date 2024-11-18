package com.example.testappdevandroid.Interface

import android.widget.Button
import com.example.testappdevandroid.Models.Cast
import com.example.testappdevandroid.Models.Genres
import com.example.testappdevandroid.Results


interface OnClickTagInterface {
    fun onClickTag(click:Boolean,position: Int,modelsTag:Int?)
}