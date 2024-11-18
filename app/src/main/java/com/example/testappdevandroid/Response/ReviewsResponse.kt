package com.example.testappdevandroid.Response

import com.example.testappdevandroid.Models.ReviewsModels
import com.example.testappdevandroid.service.MovieService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ReviewsResponse {
    private var mData : MovieService = MovieService()
    fun getReviewsMovie(movieIdR:Int,callback: Observer<ReviewsModels>){
        mData.getReviews(movieIdR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ReviewsModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: ReviewsModels) {
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