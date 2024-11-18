package com.example.testappdevandroid.Response
import com.example.testappdevandroid.Models.VideoModels
import com.example.testappdevandroid.service.MovieService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class VideoResponse {
    private var mData : MovieService = MovieService()
    fun getVideosMovie(movieIntV :Int,callback: Observer<VideoModels>){
        mData.getVideoMovie(movieIntV)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<VideoModels>{
                override fun onSubscribe(d: Disposable) {
                    callback.onSubscribe(d)
                }

                override fun onNext(t: VideoModels) {
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