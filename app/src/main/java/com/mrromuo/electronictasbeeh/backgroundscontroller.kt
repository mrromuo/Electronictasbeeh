package com.mrromuo.electronictasbeeh

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.mrromuo.electronictasbeeh.MainActivity.Companion.KEY_BACKGROUND

class backgroundscontroller : AppCompatActivity() {
      lateinit var image:ImageView
      lateinit var buton:Button
      lateinit var sheardata: SharedPreferences
      lateinit var edtor: SharedPreferences.Editor
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_backgroundscontroller)
            image = findViewById(R.id.BKGimage)
            buton =findViewById(R.id.BKGbut)
            sheardata = getSharedPreferences(MainActivity.LastState, MODE_PRIVATE)
            edtor =sheardata.edit()
            val thisImage = sheardata.getInt(KEY_BACKGROUND,1)
            var vimage =when(thisImage){
                  1 -> R.drawable.m1
                  2 ->R.drawable.m2
                  3 -> R.drawable.m3
                  else -> {R.drawable.m1}
            }
            image.setImageResource(vimage)
            image.setOnClickListener {
                  vimage = if (vimage == R.drawable.m1) R.drawable.m2 else if (vimage == R.drawable.m2) R.drawable.m3 else R.drawable.m1
                  image.setImageResource(vimage)
            }
            buton.setOnClickListener {
                  val num = when(vimage){
                        R.drawable.m1 -> 1
                        R.drawable.m2 ->2
                        R.drawable.m3 ->3
                        else -> 1
                  }
                  edtor.putInt(KEY_BACKGROUND,num)
                  edtor.commit()
                  finish()
            }
      }
}