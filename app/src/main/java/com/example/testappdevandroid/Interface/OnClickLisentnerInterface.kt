package com.example.testappdevandroid.Interface

import android.view.View
import com.example.testappdevandroid.Adapter.TitleAdpater
import com.example.testappdevandroid.Results
import java.text.FieldPosition

interface OnClickLisentnerInterface{
    fun onMovieClick(position: Int,modelsTest: List<Results>?)
}