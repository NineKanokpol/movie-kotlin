package com.example.testappdevandroid.Response

import com.example.testappdevandroid.PopularModels
import com.example.testappdevandroid.service.SearchService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchResponse {
    private var mSearch : SearchService = SearchService()
    fun getSearchMovie(pageInt: Int,nameStr:String,callback: Observer<PopularModels>){
        mSearch.getSearch(pageInt,nameStr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PopularModels?>{
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
}