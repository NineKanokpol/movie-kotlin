package com.example.testappdevandroid.Response


import com.example.testappdevandroid.Models.DetailModels
import com.example.testappdevandroid.Models.ExternalModels
import com.example.testappdevandroid.Models.Genres
import com.example.testappdevandroid.Models.KeyWordModels
import com.example.testappdevandroid.PopularModels
import com.example.testappdevandroid.service.MovieService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieResponse {
    private var mData :MovieService = MovieService()
    fun getPopularMovie(pageInt: Int,callback: Observer<PopularModels>) {
        mData.getPopularMovie(pageInt)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PopularModels?> {
                override fun onSubscribe(d: Disposable) {
//                        Log.e("Main","onSubscribe")
                    callback.onSubscribe(d)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    callback.onError(e)
                }

                override fun onComplete() {
                    //success
                    callback.onComplete()
                }

                override fun onNext(t: PopularModels) {

                    callback.onNext(t)
                }
            })
    }
    fun getTagListMovies(callback: Observer<DetailModels>){
        mData.getTagList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DetailModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: DetailModels) {
                    callback.onNext(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    callback.onError(e)
                }

                override fun onComplete() {
                    callback.onComplete()
                }

            })
    }
    fun getTagLinkList(genresStr:String,callback: Observer<PopularModels>){
        mData.getTagLink(genresStr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PopularModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: PopularModels) {
                    callback.onNext(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    callback.onError(e)
                }

                override fun onComplete() {
                    callback.onComplete()
                }

            })
    }
    fun getExternalLinkList(movieIdEx:Int,callback: Observer<ExternalModels>){
        mData.getExternalLink(movieIdEx)
            .subscribeOn(Schedulers.io())
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe(object : Observer<ExternalModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: ExternalModels) {
                    callback.onNext(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    callback.onError(e)
                }

                override fun onComplete() {
                    callback.onComplete()
                }

            })
    }
    fun getKeyWordList(movieIdKE: Int,callback: Observer<KeyWordModels>){
        mData.getKeyWordLink(movieIdKE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<KeyWordModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: KeyWordModels) {
                    callback.onNext(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    callback.onError(e)
                }

                override fun onComplete() {
                    callback.onComplete()
                }

            })

        }
    }