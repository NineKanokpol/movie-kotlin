package com.example.testappdevandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    private var webView: WebView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        web_view?.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        web_view?.loadUrl("https://www.economist.com/international?utm_medium=cpc.adword.pd&utm_source=google&utm_campaign=a.non-brand_world-news&utm_content=conversion.non-brand.anonymous&gclid=CjwKCAjwrNmWBhA4EiwAHbjEQJ3E1WBCuilLy34mp3zCcRm9nHmK__V0fQ41bw5xrK0DCDx5EwOFnBoCUDwQAvD_BwE&gclsrc=aw.ds")
        web_view?.settings?.javaScriptEnabled = true
        web_view?.settings?.allowContentAccess = true
        web_view?.settings?.domStorageEnabled = true
        web_view?.settings?.useWideViewPort = true
    }

}