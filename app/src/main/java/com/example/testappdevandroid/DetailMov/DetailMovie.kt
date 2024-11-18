package com.example.testappdevandroid.DetailMov

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testappdevandroid.*
import com.example.testappdevandroid.Adapter.*
import com.example.testappdevandroid.Models.*
import com.example.testappdevandroid.Response.*
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_full_movie.*
import kotlinx.android.synthetic.main.navbar_movie.*
import java.lang.reflect.Array.newInstance
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailMovie : AppCompatActivity() {

    private var mDataDetail: String? = null
    private var languagesDetail: List<SpokenLanguages> = ArrayList()
    private var mTitle: String? = null
    var dataMovie: Results? = null
    var imageData: String? = null
    var statusStr: String? = null
    var stringTitle: String? = null
    var imageId: ImageView? = null
    var textTitle: TextView? = null
    var textrate: Double? = null
    var stringRate: TextView? = null
    var strStatusId: String? = null
    var textOver: String? = null
    var stringOver: TextView? = null
    var idMovie: Int? = null
    var dataExternal: MovieResponse? = null
    var dataDetail: DetailFullResponse? = null
    var dataCredits: CreditsResponse? = null
    var dataReviews: ReviewsResponse? = null
    var dataVideo: VideoResponse? = null
    private var mListReview: ArrayList<String> = ArrayList()
    private var listTag: List<Genres> = ArrayList()
    private var listCasts: List<Cast> = ArrayList()
    private var listcrews: List<Crew> = ArrayList()
    private var listVideo: List<ResultsVideo> = ArrayList()
    private var listReviews : List<ResultsReviews> = ArrayList()
    var playListTag: ListAdpater? = null
    var playListCasts: CastsAdpater? = null
    var playListCrew: CrewsAdpater? = null
    var playListVideo: VideoAdpater? = null
    var playListReviews:ReviewsAdpater? = null
    var playListRecent: RecentMoviesAdapter? = null
    lateinit var moviesDB : MyDBhelper


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_movie)

        moviesDB = MyDBhelper(this)
        dataMovie = intent.getSerializableExtra("Extra_DataMovie") as? Results
        dataMovie?.title?.let { Log.e("ExtraData", it) }//check ค่าว่ามาจริงหรือไม่

        idMovie = dataMovie?.id
        dataDetail = DetailFullResponse()
        idMovie?.let { dataDetail?.getDetailMovie(it, mcallbackdetail) }


        dataCredits = CreditsResponse()
        idMovie?.let { dataCredits?.getCreditsMovie(it, mcallbackCredits) }

        dataVideo = VideoResponse()
        idMovie?.let { dataVideo?.getVideosMovie(it, mcallbackVideo) }

        dataReviews = ReviewsResponse()
        idMovie?.let { dataReviews?.getReviewsMovie(it, mcallbackReviews) }

        dataExternal = MovieResponse()
        idMovie?.let { dataExternal?.getExternalLinkList(it,mCallbackExternal) }

        idMovie?.let { dataExternal?.getKeyWordList(it,mcallbackKeyword) }

        btn_detail_casts.setOnClickListener{
            var castsView:Intent = Intent(this,DetailCasts::class.java)
            castsView.putExtra("dataAll",dataMovie)
            startActivity(castsView)
        }

        home_view.setOnClickListener {
            var homeView: Intent = Intent(this, AfterLogin::class.java)
            startActivity(homeView)
        }

        go_to_search.setOnClickListener {
            var search = Intent(this,TestSearch::class.java)
            startActivity(search)
        }

//        btn_to_up.setOnClickListener {
//            scroll.post {
//                scroll.fullScroll(View.FOCUS_UP)
//            }
//        }



        showTextOriginalTitle()
        showImageDetail()
        showTextTitle()
        showRate()
        showOverView()
        showRecentMovies()
    }
    fun showRecentMovies(){
        var moviesRecent = moviesDB.readMovie()
        recycle_view_recent?.layoutManager = GridLayoutManager(this@DetailMovie,3)
        playListRecent = RecentMoviesAdapter(this@DetailMovie,moviesRecent)
        recycle_view_recent?.adapter = playListRecent
    }
    var mcallbackKeyword = object : Observer<KeyWordModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: KeyWordModels) {
            var listKeywords = t.keywords

            recycle_view_key_words.layoutManager = GridLayoutManager(this@DetailMovie,3)
            var playListKeywords = KeyWordsAdpater(this@DetailMovie,listKeywords)
            recycle_view_key_words.adapter = playListKeywords
        }

        override fun onError(e: Throwable) {
            TODO("Not yet implemented")
        }

        override fun onComplete() {
            Toast.makeText(this@DetailMovie,"successKeyWord!",Toast.LENGTH_LONG).show()
        }

    }
    var mCallbackExternal = object : Observer<ExternalModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: ExternalModels) {
            link_facebook.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+t.facebookId))
                startActivity(browserIntent)
            }
            link_instragram.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"+t.instagramId))
                startActivity(browserIntent)
            }
            link_twitter.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+t.instagramId))
                startActivity(browserIntent)
            }

        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }

    }
    var mcallbackReviews = object : Observer<ReviewsModels>{
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: ReviewsModels) {
            listReviews = t.results.take(2)


            recycle_review?.layoutManager = LinearLayoutManager(this@DetailMovie)
            playListReviews = ReviewsAdpater(this@DetailMovie,listReviews)
            recycle_review?.adapter = playListReviews


        }

        override fun onError(e: Throwable) {
            Toast.makeText(this@DetailMovie, "Error!", Toast.LENGTH_LONG).show()
        }

        override fun onComplete() {
            Toast.makeText(this@DetailMovie, "SuccessGetReviews!", Toast.LENGTH_LONG).show()
        }

    }

    var mcallbackVideo = object : Observer<VideoModels> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: VideoModels) {
            listVideo = t.results

            var takeVideo = listVideo.take(4)


//            recyclerViewTag = findViewById(R.id.card_view_movie)
            card_view_movie?.layoutManager = LinearLayoutManager(this@DetailMovie)
            playListVideo = VideoAdpater(this@DetailMovie, takeVideo)
            card_view_movie?.adapter = playListVideo

        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }

    }
    var mcallbackCredits = object : Observer<CreditsModels> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: CreditsModels) {
            listCasts = t.cast
            listcrews = t.crew

            var takeCasts = listCasts.take(6)
            var takeCrews = listcrews.take(6)

//            recyclerViewTag = findViewById(R.id.card_view_casts)
            card_view_casts?.layoutManager = GridLayoutManager(this@DetailMovie, 3)
            playListCasts = CastsAdpater(this@DetailMovie, takeCasts)
            card_view_casts?.adapter = playListCasts

//            recyclerViewTag = findViewById(R.id.card_view_crews)
            card_view_crews?.layoutManager = GridLayoutManager(this@DetailMovie, 3)
            playListCrew = CrewsAdpater(this@DetailMovie, takeCrews)
            card_view_crews?.adapter = playListCrew

        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }

    }

    var mcallbackdetail = object : Observer<DetailModels> {
        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: DetailModels) {

            listTag = t.genres

            link_website.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(t.homepage))
                startActivity(browserIntent)
            }

            showDateRe(t.releaseDate)
            showStatus(t.status)
            showBudget(t.budget)
            showRevenue(t.revenue)
            showRunTime(t.runtime)


            recycle_view_list?.layoutManager = GridLayoutManager(
                this@DetailMovie, 2,
                GridLayoutManager.HORIZONTAL, false
            )
            playListTag = ListAdpater(this@DetailMovie, listTag)
            recycle_view_list?.adapter = playListTag


        }

        override fun onError(e: Throwable) {
            Toast.makeText(this@DetailMovie, "Error!", Toast.LENGTH_LONG).show()

        }

        override fun onComplete() {
            Toast.makeText(this@DetailMovie, "Success!", Toast.LENGTH_LONG).show()
        }
    }
    @SuppressLint("SetTextI18n")
    fun showRunTime(runTime: Int?){

        textTitle = findViewById(R.id.time_detail)

        textTitle?.text = runTime.toString() + " minute"
    }

    fun showBudget(budgetMov: Int?) {

        textTitle = findViewById(R.id.budget)

        val amount = budgetMov?.toDouble()
        val formatter = DecimalFormat("#,###")
        var strNum = formatter.format(amount)

        textTitle?.text = "$" + strNum.toString()
    }

    fun showRevenue(revenueData: Int?) {

        val amount = revenueData?.toDouble()
        val formatter = DecimalFormat("#,###")
        var strNum = formatter.format(amount)

        textTitle = findViewById(R.id.revenue)

        textTitle?.text = "$" + strNum.toString()
    }

    fun showStatus(strStatus: String?) {
        textTitle = findViewById(R.id.status_re)

        textTitle?.text = strStatus
    }

    fun showTextOriginalTitle() {
        stringTitle = dataMovie?.originalTitle

        textTitle = findViewById(R.id.original_title)

        textTitle?.text = stringTitle.toString()
    }

    fun showImageDetail() {
        var pathImage = "https://image.tmdb.org/t/p/w500/"
        imageData = pathImage + dataMovie?.posterPath

        imageId = findViewById(R.id.image_view_each)

        Glide
            .with(this)
            .load(imageData)
            .into(image_view_each)
    }

    fun showDateRe(statusPara: String?) {

        textTitle = findViewById(R.id.date_re)

        val outputFormat: DateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val date: Date = statusPara?.let { inputFormat.parse(it) } as Date
        val outputText: String = outputFormat.format(date)
        textTitle?.text = outputText

    }

    fun showTextTitle() {
        stringTitle = dataMovie?.title

        textTitle = findViewById(R.id.text_title)

        textTitle?.text = stringTitle.toString()
    }

    fun showRate() {
        textrate = dataMovie?.voteAverage



        stringRate = findViewById(R.id.rate_detail)


        stringRate?.text = textrate.toString()

//        covDouToStr.let {
//            if (it != null) {
//                Log.e("Cov", it)
//            }
//        }

    }

    fun showOverView() {
        textOver = dataMovie?.overview

        stringOver = findViewById(R.id.content_movie)

        stringOver?.text = textOver.toString()
    }

//    fun getDataDetail() {
//        dataDetailget = DetailResponse()
//        dataDetailget?.getDetailMovie(mCallbackDetail)
//    }

//    var mCallbackDetail = object : Observer<ModelsTest> {
//        override fun onSubscribe(d: Disposable) {
//
//        }
//
//        override fun onNext(t: ModelsTest) {
//
//        }
//
//        override fun onError(e: Throwable) {
//
//        }
//
//        override fun onComplete() {
//
//        }
    //get Title Detail from API
//            mTitle = t.originalTitle
//            var mTitleMain: TextView = findViewById(R.id.text_title)
//            mTitleMain.text = mTitle.toString()
//
//            //get Rate Detail from API
//            var mRateTtitle = t.voteAverage
//            var RateTitle: TextView = findViewById(R.id.rate_detail)
//            RateTitle.text = mRateTtitle.toString()
//
//
//            //get photo to API
//            Glide
//                .with(this@DetailMovie) //ต้องการ contect
//                .load(fullImage)
//                .into(view_image);
}

