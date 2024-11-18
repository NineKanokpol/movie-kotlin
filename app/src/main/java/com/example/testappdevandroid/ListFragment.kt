package com.example.testappdevandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappdevandroid.Adapter.CrewsFragmentAdapter
import com.example.testappdevandroid.Adapter.HomeAdpater
import com.example.testappdevandroid.Models.CreditsModels
import com.example.testappdevandroid.Models.Crew
import com.example.testappdevandroid.Response.CreditsResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ListFragment(private var listResults: Results) : Fragment() {
    var dataGet : CreditsResponse? = null
    var dataid : Int? = null
    var recyclerViewPage: RecyclerView? = null
    var mSendData : List<Crew> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataCrews()

    }
    fun dataCrews(){
        dataid = listResults.id
        dataGet = CreditsResponse()
        dataid?.let { dataGet?.getCreditsMovie(it,callbackcrews) }
    }
    var callbackcrews = object : Observer<CreditsModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: CreditsModels) {
            mSendData = t.crew

            recyclerViewPage = view?.findViewById(R.id.recycle_view_crews)
            var decoration: DividerItemDecoration? = DividerItemDecoration(context, LinearLayout.VERTICAL) //การใส่เส้น
            getActivity()?.let {
                ContextCompat.getDrawable(it,R.drawable.divider)
                    ?.let { decoration?.setDrawable(it) }
            }
            decoration?.let { recyclerViewPage?.addItemDecoration(it) }
            recyclerViewPage?.layoutManager = LinearLayoutManager(context)
            var playListFragment = context?.let { CrewsFragmentAdapter(it,mSendData) }
            recyclerViewPage?.adapter = playListFragment
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    }
}