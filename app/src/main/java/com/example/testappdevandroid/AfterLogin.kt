package com.example.testappdevandroid

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Adapter.TitleAdpater
import com.example.testappdevandroid.DetailMov.DetailMovie
import com.example.testappdevandroid.Interface.OnClickLisentnerInterface
import com.example.testappdevandroid.Response.MovieResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_after_login.*
import kotlinx.android.synthetic.main.navbar_movie.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AfterLogin : AppCompatActivity() {
    var dataGet: MovieResponse? = null
    lateinit var movieDB : MyDBhelper
    private var mSendData:List<Results> = ArrayList()
    private var mListTitle:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_login)

        getDataModels()
        btnViewMore()

        movieDB = MyDBhelper(this)

        mSendData.forEach {
            it.title?.let { title -> mListTitle.add(title) }
        }

        home_view.setOnClickListener {
            var homeView:Intent = Intent(this,AfterLogin::class.java)
            startActivity(homeView)
        }
        go_to_search.setOnClickListener{
            var testBtn: Intent = Intent(this,TestSearch::class.java)
            startActivity(testBtn)

        }

    }
    fun getDataModels(){
        dataGet = MovieResponse()
        dataGet?.getPopularMovie(1,mCallbackPopular)
    }

    var mCallbackPopular = object : Observer<PopularModels> {
        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: PopularModels) {
            recycle_view.visibility = View.VISIBLE
            mSendData = t.results
            Log.d("Data","Datam"+mSendData)
            var recyclerView: RecyclerView = findViewById(R.id.recycle_view)
            //----
            var decoration: DividerItemDecoration? = DividerItemDecoration(this@AfterLogin, LinearLayout.VERTICAL) //การใส่เส้น
            decoration?.let { recyclerView.addItemDecoration(it) }
            //-----

            recyclerView.layoutManager = GridLayoutManager(this@AfterLogin,2)
            var playListAdapter = TitleAdpater(this@AfterLogin,mSendData)
            recyclerView.adapter = playListAdapter
            playListAdapter.setOnMovieClickLisener(mOnMovieCLickLisener)

        }

        override fun onError(e: Throwable) {

        }

        override fun onComplete() {//ถ้า success
            progress_view.visibility = View.GONE
        }
    }
    var mOnMovieCLickLisener = object : OnClickLisentnerInterface {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onMovieClick(position:Int, results: List<Results>?) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            var nextpage: Intent = Intent(this@AfterLogin,DetailMovie::class.java)
            nextpage.putExtra("Extra_DataMovie", results?.get(position))
            Log.d("testData",results?.get(position).toString())
            var list = results?.get(position)
            startActivity(nextpage)
            var movieId = results?.get(position)?.id
            var nameMovie = results?.get(position)?.title
            var image = results?.get(position)?.posterPath
            movieDB.insertMovies(Results(id = movieId, title = nameMovie, posterPath = image, releaseDate = formatted))
        }
    }
    fun btnViewMore(){
        btnView_more.setOnClickListener {
            var nextViewMore: Intent = Intent(this@AfterLogin,ViewMoreMovie::class.java)
            startActivity(nextViewMore)
        }
    }



}

