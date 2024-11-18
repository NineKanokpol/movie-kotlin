package com.example.testappdevandroid.Response

import com.example.testappdevandroid.Models.CreditsModels
import com.example.testappdevandroid.service.MovieService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreditsResponse {
    private var mData : MovieService = MovieService()
    fun getCreditsMovie(movieIntC :Int,callback: Observer<CreditsModels>){
        mData.getCreditsMovie(movieIntC)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CreditsModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: CreditsModels) {
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