package com.mrromuo.electronictasbeeh


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.LinearLayout

class Help : AppCompatActivity() {
      //lateinit var webViewTitle: TextView
      private lateinit var webViewWeb: WebView
      private lateinit var weblayout: LinearLayout

      @SuppressLint("SetJavaScriptEnabled")
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_help)
            setSupportActionBar(findViewById(R.id.helpToolBar))

            //webViewTitle = findViewById(R.id.webViewText)
            weblayout = findViewById(R.id.helpviewer)
            webViewWeb = findViewById(R.id.webViewWeb)
            val intent = intent.extras
            val myJob = intent?.getInt(MainActivity.KEY_HELP,1)
            val website  = if(myJob == 1) "https://mrromuo.com/electronic-tasbeeh-app/" else "https://mrromuo.com/programing/electronic-tasbeeh-app/privacy-policy/"
            val text = if (myJob ==1)getText(R.string.help) else getString(R.string.polcy)
            //webViewTitle.text = text
            webViewWeb.settings.javaScriptEnabled = true
            webViewWeb.settings.setSupportZoom(true)
            webViewWeb.loadUrl(website)
      }
      override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.helpmenu, menu)
            return true
      }

      override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val intent: Intent = when (item.itemId)
            {
                  R.id.Hedit -> Intent(this, EditingActivity::class.java)
                  R.id.HMain -> Intent(this, MainActivity::class.java)
                  R.id.Hchangebackground-> Intent(this, BackgroundsController::class.java)
                  R.id.Hpolsy -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 2)
                  else -> Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            return super.onOptionsItemSelected(item)
      }

}