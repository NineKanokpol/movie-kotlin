package com.example.testappdevandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Adapter.HomeAdpater
import com.example.testappdevandroid.Models.Cast
import com.example.testappdevandroid.Models.CreditsModels
import com.example.testappdevandroid.Response.CreditsResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.navbar_movie.*

class HomeFragment(private var listResults: Results): Fragment() {
    var dataGet : CreditsResponse? = null
    var mSendData : List<Cast> = ArrayList()
    var recyclerViewPage:RecyclerView? = null
    var dataMovie:Int? = null
    var dBhelper: MyDBhelper? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        extFloatingActionButton.setOnClickListener {
            nestedScrollView.post {
                nestedScrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
        val extendedFloatingActionButton = view.findViewById<FloatingActionButton>(R.id.extFloatingActionButton)
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> // the delay of the extension of the FAB is set for 12 items

            if (scrollY > oldScrollY + 12 && extendedFloatingActionButton.isShown) {
                extendedFloatingActionButton.hide()
            }

            // the delay of the extension of the FAB is set for 12 items
            if (scrollY < oldScrollY - 12 && !extendedFloatingActionButton.isShown) {
                extendedFloatingActionButton.show()
            }

            // if the nestedScrollView is at the first item of the list then the
            // floating action should be in show state
            if (scrollY == 0) {
                extendedFloatingActionButton.show()
            }
        })

        dataCredits()

    }
    fun dataCredits(){
        dataMovie = listResults?.id
        dataGet = CreditsResponse()
        dataMovie?.let { dataGet?.getCreditsMovie(it,mCallBackCredits) }
    }
    var mCallBackCredits = object : Observer<CreditsModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: CreditsModels) {
            extFloatingActionButton.visibility = View.VISIBLE
            recycle_view_home.visibility = View.VISIBLE
            mSendData = t.cast

            recyclerViewPage = view?.findViewById(R.id.recycle_view_home)
            var decoration: DividerItemDecoration? = DividerItemDecoration(context, LinearLayout.VERTICAL) //การใส่เส้น
            getActivity()?.let {
                ContextCompat.getDrawable(it,R.drawable.divider)
                    ?.let { decoration?.setDrawable(it) }
            }
            decoration?.let { recyclerViewPage?.addItemDecoration(it) }
            recyclerViewPage?.layoutManager = LinearLayoutManager(context)
            var playListFragment = context?.let { HomeAdpater(it,mSendData) }
            recyclerViewPage?.adapter = playListFragment
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            progress_home.visibility = View.GONE
            Toast.makeText(context,"SuccessFragment",Toast.LENGTH_LONG).show()
        }

    }

}