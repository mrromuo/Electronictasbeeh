package com.mrromuo.electronictasbeeh

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.mrromuo.electronictasbeeh.MainActivity.Companion.KEY_BACKGROUND

class BackgroundsController : AppCompatActivity() {
      private lateinit var image:ImageView
      private lateinit var buton:Button
      private lateinit var sheardata: SharedPreferences
      private lateinit var edtor: SharedPreferences.Editor

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_backgroundscontroller)
            setSupportActionBar(findViewById(R.id.changToolBar))
            image = findViewById(R.id.BKGimage)
            buton =findViewById(R.id.BKGbut)
            sheardata = getSharedPreferences(MainActivity.LastState, MODE_PRIVATE)
            edtor =sheardata.edit()
            val thisImage = sheardata.getInt(KEY_BACKGROUND,1)
            var vimage =when(thisImage){
                  1 -> R.drawable.m1
                  2 ->R.drawable.m2
                  3 -> R.drawable.m38
                  else -> {R.drawable.m1}
            }
            image.setImageResource(vimage)
            image.setOnClickListener {
                  vimage = if (vimage == R.drawable.m1) R.drawable.m2 else if (vimage == R.drawable.m2) R.drawable.m38 else R.drawable.m1
                  image.setImageResource(vimage)
            }
            buton.setOnClickListener {
                  val num = when(vimage){
                        R.drawable.m1 -> 1
                        R.drawable.m2 ->2
                        R.drawable.m38 ->3
                        else -> 1
                  }
                  edtor.putInt(KEY_BACKGROUND,num)
                  edtor.commit()
                  finish()
            }
      }
      override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.changemenu, menu)
            return true
      }

      override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val intent: Intent = when (item.itemId)
            {
                  R.id.Cedit -> Intent(this, EditingActivity::class.java)
                  R.id.Cmain -> Intent(this, MainActivity::class.java)
                  R.id.Chelp -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 1)
                  R.id.Cpolsy -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 2)
                  else -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 1)
            }
            startActivity(intent)
            return super.onOptionsItemSelected(item)
      }
}