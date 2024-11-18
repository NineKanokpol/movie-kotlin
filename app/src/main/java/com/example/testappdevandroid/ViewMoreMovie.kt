package com.example.testappdevandroid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.testappdevandroid.Adapter.ListAdpater
import com.example.testappdevandroid.Adapter.TitleAdpater
import com.example.testappdevandroid.DetailMov.DetailMovie
import com.example.testappdevandroid.Interface.OnClickLisentnerInterface
import com.example.testappdevandroid.Interface.OnClickTagInterface
import com.example.testappdevandroid.Models.DetailModels
import com.example.testappdevandroid.Models.Genres
import com.example.testappdevandroid.Response.CreditsResponse
import com.example.testappdevandroid.Response.DetailFullResponse
import com.example.testappdevandroid.Response.MovieResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_viewmore_movie.*
import kotlinx.android.synthetic.main.navbar_movie.*
import kotlin.text.split as split1


class ViewMoreMovie : AppCompatActivity() {
    var dataPage:MovieResponse? = null
    var dataTag:MovieResponse? = null
    private var listMovieAll = ArrayList<Results>()
    private var listTagAll = ArrayList<Results>()
    private var pageAll:List<Results> = ArrayList()
    private var mSendData:List<Genres> = ArrayList()
    private var countNum = 1
    var playListAdapter:TitleAdpater? = null
    var recycleViewpage: RecyclerView? = null
    var recyclerViewTag: RecyclerView? = null
    var playListFilter:ListAdpater? = null
    var playListTag:TitleAdpater? = null
    var isvisible = false
    var dataCredits: DetailFullResponse? = null
    var listInt = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmore_movie)

        getDataPageOn()

        val actionbar = supportActionBar
        actionbar?.title = "ALLMOVIE"
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        dataPage = MovieResponse()
        dataPage?.getTagListMovies(mcallbackTagList)


        home_view.setOnClickListener {
            var homeView = Intent(this,AfterLogin::class.java)
            startActivity(homeView)
        }
        go_to_search.setOnClickListener {
            var search = Intent(this,TestSearch::class.java)
            startActivity(search)
        }
        btn_filter.setOnClickListener {
            if (!isvisible) {
                btn_down.setImageResource(R.drawable.ic_up)
                view_list.visibility = View.VISIBLE
                val animationSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
                view_list.startAnimation(animationSlideDown)
                isvisible = true
            }else {
                btn_down.setImageResource(R.drawable.ic_down)
                val animationSlideUp = AnimationUtils.loadAnimation(this,R.anim.slide_up)
                view_list.startAnimation(animationSlideUp)
                isvisible = false
                Handler().postDelayed({
                    view_list.visibility = View.GONE
                }, 1000)
            }
        }
//        search_view.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//            }
//
//        })

        recycleViewpage = findViewById(R.id.recycle_page1)
        var decoration: DividerItemDecoration? = DividerItemDecoration(this@ViewMoreMovie, LinearLayout.VERTICAL) //การใส่เส้น
        decoration?.let { recycleViewpage?.addItemDecoration(it) }
        recycleViewpage?.layoutManager = GridLayoutManager(this@ViewMoreMovie, 2)
        playListAdapter = TitleAdpater(this@ViewMoreMovie, listMovieAll)
        recycleViewpage?.adapter = playListAdapter
        playListAdapter?.setOnMovieClickLisener(mOnMovieCLickList)


        recycleViewpage?.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    dataPage?.getPopularMovie(countNum,mCallbackPageOne)
                    Toast.makeText(this@ViewMoreMovie, "Last", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun getDataPageOn(){
        dataPage = MovieResponse()
        dataPage?.getPopularMovie(countNum,mCallbackPageOne)
    }
    var mcallbackTagList = object : Observer<DetailModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: DetailModels) {
            mSendData = t.genres

            recycle_view_filter.layoutManager = GridLayoutManager(this@ViewMoreMovie,3)
            var playListTag = ListAdpater(this@ViewMoreMovie,mSendData)
            recycle_view_filter.adapter = playListTag
            playListTag.setOnclickTag(mOnclickTag)
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Toast.makeText(this@ViewMoreMovie,"GetTag!",Toast.LENGTH_LONG).show()
        }

    }

    var mCallbackPageOne = object : Observer<PopularModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: PopularModels) {


            listMovieAll?.addAll(t.results)
            playListAdapter?.notifyDataSetChanged()
            countNum += 1

//            playListAdapter?.notifyDataSetChanged()//ใช้ update adapter

        }
        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Toast.makeText(this@ViewMoreMovie,"Success!",Toast.LENGTH_LONG).show()
        }

    }
    var mOnMovieCLickList = object : OnClickLisentnerInterface {
        override fun onMovieClick(position:Int,results: List<Results>?) {
            var nextpage: Intent = Intent(this@ViewMoreMovie, DetailMovie::class.java)
            nextpage.putExtra("Extra_DataMovie", results?.get(position))
            startActivity(nextpage)
            Toast.makeText(this@ViewMoreMovie, "item", Toast.LENGTH_LONG).show()
        }
    }
    var mOnclickTag = object : OnClickTagInterface{
        override fun onClickTag(click:Boolean,position: Int, modelsTag: Int?) {
            var str : String? = null
//            Toast.makeText(this@ViewMoreMovie,"Click!",Toast.LENGTH_LONG).show()
            if(click==true){
                modelsTag?.let { listInt.add(it) }
                val sperator = ","
                str = listInt.joinToString(sperator, transform = Int::toString)
            }
            else{
                modelsTag.let { listInt.remove(it) }
            }
            dataTag = MovieResponse()
            str?.let { dataTag?.getTagLinkList(it,mcallbackTagLink) }
        }

    }
    var mcallbackTagLink = object : Observer<PopularModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: PopularModels) {

            listMovieAll.clear()
            listMovieAll?.addAll(t.results)
//            playListAdapter = TitleAdpater(this@ViewMoreMovie, listMovieAll)
            playListAdapter?.notifyDataSetChanged()

        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }

    }

}