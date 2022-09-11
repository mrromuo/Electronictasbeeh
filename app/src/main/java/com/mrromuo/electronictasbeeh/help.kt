package com.mrromuo.electronictasbeeh

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.TextView

class help : AppCompatActivity() {
      lateinit var webViewTitle: TextView
      lateinit var webViewWeb: WebView
      lateinit var weblayout: LinearLayout
      @SuppressLint("SetJavaScriptEnabled")
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_help)
            webViewTitle = findViewById(R.id.webViewText)
            weblayout = findViewById(R.id.helpviewer)
            webViewWeb = findViewById(R.id.webViewWeb)
            val intent = intent.extras
            val myJob = intent?.getInt(MainActivity.KEY_HELP,1)
            val website  = if(myJob == 1) "https://mrromuo.com/electronic-tasbeeh-app/" else "https://mrromuo.com/programing/electronic-tasbeeh-app/privacy-policy/"
            val text = if (myJob ==1)getText(R.string.help) else getString(R.string.polcy)
            webViewTitle.text = text
            webViewWeb.settings.javaScriptEnabled = true
            webViewWeb.settings.setSupportZoom(true)
            webViewWeb.loadUrl(website)
      }
}