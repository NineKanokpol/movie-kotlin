package com.example.testappdevandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Adapter.SearchAdpater
import com.example.testappdevandroid.DetailMov.DetailMovie
import com.example.testappdevandroid.Interface.OnClickSearchInterface
import com.example.testappdevandroid.Response.SearchResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_test_search.*


class TestSearch : AppCompatActivity() {
    var dataGet: SearchResponse? = null
    private var mSendData:List<Results> = ArrayList()
    private var mListTitle:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_search)


        mSendData.forEach {
            it.title?.let { title -> mListTitle.add(title) }
        }

        search_test.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (mListTitle.contains(query)) {
                } else {
                    Toast.makeText(this@TestSearch, "No Match Found", Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dataGet = SearchResponse()
                dataGet?.getSearchMovie(1,newText.toString(),mCallbackTitle)
                return false
            }
        })
        linearView.setOnClickListener {
            hideKeyboard(it)
        }
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    var mCallbackTitle = object : Observer<PopularModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: PopularModels) {
            mSendData = t.results
            var recyclerView: RecyclerView = findViewById(R.id.recycle_view_Title)
            //----
            var decoration: DividerItemDecoration? = DividerItemDecoration(this@TestSearch, LinearLayout.VERTICAL) //การใส่เส้น
            decoration?.let { recyclerView.addItemDecoration(it) }
            //-----

            recyclerView.layoutManager = LinearLayoutManager(this@TestSearch)
            var playListAdapter = SearchAdpater(this@TestSearch,mSendData)
            recyclerView.adapter = playListAdapter
            playListAdapter.setOnMovieSearchClick(mOnMovieCLickSearch)
        }

        override fun onError(e: Throwable) {
            Toast.makeText(this@TestSearch,"Error!",Toast.LENGTH_LONG).show()
        }

        override fun onComplete() {
            Toast.makeText(this@TestSearch,"Success!",Toast.LENGTH_LONG).show()
        }

    }
    var mOnMovieCLickSearch = object : OnClickSearchInterface {
        override fun onClickMovie(position:Int,results: List<Results>?) {
            var nextpage: Intent = Intent(this@TestSearch, DetailMovie::class.java)
            nextpage.putExtra("Extra_DataMovie", results?.get(position))
            startActivity(nextpage)
            Toast.makeText(this@TestSearch, "item", Toast.LENGTH_LONG).show()
        }
    }

}