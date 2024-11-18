package com.example.testappdevandroid.service


import com.example.testappdevandroid.Interface.APIinterface
import com.example.testappdevandroid.Models.*
import com.example.testappdevandroid.PopularModels
import com.example.testappdevandroid.ShootAPI.APIgetRetrofit
import io.reactivex.Observable
import io.reactivex.Observer

class MovieService : APIgetRetrofit() {
    var apiKey = "0130b4b041b9f7ad823e5e79ccf40bb2"

    fun getInterface(): APIinterface {
        return movieService().create(APIinterface::class.java)
    }

    fun getPopularMovie(page:Int): Observable<PopularModels> {
        return getInterface().getAPItest(apiKey,page)
    }

    fun getDetailMovie(movieId: Int) : Observable<DetailModels> {
        return  getInterface().getApiDetail(movieId,apiKey)
    }
    fun getCreditsMovie(movieId: Int) : Observable<CreditsModels>{
        return getInterface().getApiCredits(movieId,apiKey)
    }
    fun getVideoMovie(movieId: Int) : Observable<VideoModels>{
        return getInterface().getAPIVideo(movieId,apiKey)
    }
    fun getReviews(movieId: Int): Observable<ReviewsModels>{
        return getInterface().getAPIReviews(movieId,apiKey)
    }
    fun getTagList(): Observable<DetailModels>{
        return getInterface().getTagMovies(apiKey)
    }
    fun getTagLink(genresId:String): Observable<PopularModels>{
        return getInterface().getTagLink(apiKey,genresId)
    }
    fun getExternalLink(movieId: Int): Observable<ExternalModels>{
        return getInterface().getExternal(movieId,apiKey)
    }
    fun getKeyWordLink(movieId: Int): Observable<KeyWordModels>{
        return getInterface().getKeyword(movieId,apiKey)
    }
}