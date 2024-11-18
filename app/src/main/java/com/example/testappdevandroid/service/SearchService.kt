package com.example.testappdevandroid.service

import android.content.Intent
import com.example.testappdevandroid.Interface.APIinterface
import com.example.testappdevandroid.PopularModels
import com.example.testappdevandroid.ShootAPI.APIgetRetrofit
import io.reactivex.Observable

class SearchService : APIgetRetrofit() {
    var apiKey = "0130b4b041b9f7ad823e5e79ccf40bb2"

    fun getInterfaceSearch(): APIinterface {
        return movieService().create(APIinterface::class.java)
    }
    fun getSearch(page:Int, name:String) : Observable<PopularModels> {
        return getInterfaceSearch().getApiSearch(apiKey,name,page)
    }
}