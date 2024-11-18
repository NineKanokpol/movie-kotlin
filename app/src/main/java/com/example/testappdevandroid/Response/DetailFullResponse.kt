package com.example.testappdevandroid.Response

import com.example.testappdevandroid.Models.DetailModels
import com.example.testappdevandroid.service.MovieService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailFullResponse {
    private var mData : MovieService = MovieService()
    fun getDetailMovie(movieIdInt: Int, callback: Observer<DetailModels>){
        mData.getDetailMovie(movieIdInt)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DetailModels> {
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
}