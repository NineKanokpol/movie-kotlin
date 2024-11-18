package com.example.testappdevandroid.DetailMov

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.testappdevandroid.HomeFragment
import com.example.testappdevandroid.R
import com.example.testappdevandroid.Results
import com.example.testappdevandroid.TabPageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_casts_movie_detail.*

class DetailCasts : AppCompatActivity() {
    var adapterPager:TabPageAdapter? = null
    var datamovie:Results? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casts_movie_detail)

        datamovie = intent.getSerializableExtra("dataAll") as Results?
        setUpTabBar()


//        dataCasts = intent.getSerializableExtra("Extra_casts") as? Int
////        Log.e("DataID", dataCasts.toString())
//
//        getDataCasts()
    }

    fun changeIconTab(position: Int, @DrawableRes drawable: Int) {
        val view: View? = adapterPager?.getTabView(position)
        val tabBarIcon = view?.findViewById<ImageView>(R.id.home_black_icon)
        tabBarIcon?.setColorFilter(Color.BLUE)
        tabBarIcon?.setImageResource(drawable)
        adapterPager?.setTab(position, view)
    }
//    fun colorTap(@DrawableRes drawable:Int,@DrawableRes drawable1: Int,@DrawableRes drawable2: Int){
//        changeIconTab(0,drawable)
//        changeIconTab(1,drawable1)
//        changeIconTab(2,drawable2)
//    }

    private fun setUpTabBar() {
        adapterPager = datamovie?.let { TabPageAdapter(this@DetailCasts,tab_layout.tabCount, it) }
        viewPager.adapter = adapterPager

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                tab_layout.selectTab(tab_layout.getTabAt(position))
            }
        })
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                val tabIconColor : Int = ContextCompat.getColor(this@DetailCasts,R.color.blue)
                tabIconColor.let { tab.icon?.setColorFilter(it, PorterDuff.Mode.SRC_IN) }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabIconColor: Int = ContextCompat.getColor(this@DetailCasts,R.color.black)
                tabIconColor.let { tab?.icon?.setColorFilter(it, PorterDuff.Mode.SRC_IN) }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

}
