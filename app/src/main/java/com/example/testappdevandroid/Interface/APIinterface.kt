package com.example.testappdevandroid.Interface

import com.example.testappdevandroid.Models.*
import com.example.testappdevandroid.PopularModels
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.Path

interface APIinterface {
    @GET("movie/popular")
    fun getAPItest(
        @Query("api_key") apiKey: String,
        @Query("page") pageKey: Int
    ): Observable<PopularModels>

    @GET("search/movie")
    fun getApiSearch(
        @Query("api_key") apiKey : String,
        @Query("query") queryKey : String,
        @Query("page") pageKey: Int
    ): Observable<PopularModels>

    @GET("movie/{movie_id}")
    fun getApiDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey: String,
    ): Observable<DetailModels>

    @GET("movie/{movie_id}/credits")
    fun getApiCredits(
        @Path("movie_id") movieIdC : Int,
        @Query("api_key") apiKey: String
    ): Observable<CreditsModels>

    @GET("movie/{movie_id}/videos")
    fun getAPIVideo(
        @Path("movie_id") movieIdV : Int,
        @Query("api_key") apiKey: String
    ): Observable<VideoModels>

    @GET("movie/{movie_id}/reviews")
    fun getAPIReviews(
        @Path("movie_id") movieIdR : Int,
        @Query("api_key") apiKey: String
    ): Observable<ReviewsModels>

    @GET("genre/movie/list")
    fun getTagMovies(
        @Query("api_key") apiKey: String
    ): Observable <DetailModels>

    @GET("discover/movie")
    fun getTagLink(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genres: String
    ): Observable<PopularModels>

    @GET("movie/{movie_id}/external_ids")
    fun getExternal(
        @Path("movie_id") movieIdEx:Int,
        @Query("api_key") apiKey: String
    ): Observable<ExternalModels>

    @GET("movie/{movie_id}/keywords")
    fun getKeyword(
        @Path("movie_id") movieIdkey:Int,
        @Query("api_key") apiKey: String
    ): Observable<KeyWordModels>
}