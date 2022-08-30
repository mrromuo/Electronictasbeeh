package com.mrromuo.electronictasbeeh

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
      var listView:ListView? =null
      var textView:TextView? = null
      var seqbar:ProgressBar? = null
      var button:Button? = null
      var thekerTx:TextView? = null
      var num:TextView? =null

      lateinit var data :DataHelper
      lateinit var adpter:ArrayAdapter<String>
      companion object{
             var list:ArrayList<Adkar> = ArrayList()
             var thkrList:ArrayList<String> = ArrayList()
             var thkrCounter = 0
             var setCounter = 34
            const val POSITION = "POSITION"
      }
      @RequiresApi(Build.VERSION_CODES.S)
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            list = DataHelper(this).readMyDataBs()
            listView = findViewById(R.id.selectingList)
            textView = findViewById(R.id.textView)
            thekerTx = findViewById(R.id.thekeerTV)
            num = findViewById(R.id.TargwtNum)
            seqbar = findViewById(R.id.progressBar)
            button = findViewById(R.id.button)

            //  ==========    VIBRATOR_MANAGER_SERVICE ============
            val vibratorManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                  val vibratorManager =
                        getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                  vibratorManager.defaultVibrator
            } else {
                  @Suppress("DEPRECATION")
                  getSystemService(VIBRATOR_SERVICE) as Vibrator
            }
            //=================================================

            data = DataHelper(this)


            //                    ========
            listView?.setOnItemClickListener(){ parent,view , position, id->
                  thekerTx?.text = thkrList[position]
                  val counterValue = list[position]
                  setCounter = counterValue.Count
                  num?.text = counterValue.Count.toString()
                  thkrCounter = 0
                  textView?.text = thkrCounter.toString()
                  seqbar?.max = setCounter
                  seqbar?.progress = thkrCounter
            }
            //                     ==========
            listView?.setOnItemLongClickListener { parent, view, position, id ->
                 Toast.makeText(this, "Position long Clicked:"+" "+position,Toast.LENGTH_SHORT).show()
                  val intent=Intent(this@MainActivity,EditingActivity::class.java)
                  //intent.extras?.putInt("POSITION",position)
                  intent.putExtra(POSITION,position)

                  startActivity(intent)

                  return@setOnItemLongClickListener(true)
            }
            //                         ==========
            button?.setOnClickListener(){
                  thkrCounter = if (thkrCounter < setCounter) {
                        thkrCounter+1 }else{
                        vibratorManager.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
                        thkrCounter}
                  textView?.text = thkrCounter.toString()
                  seqbar?.progress= thkrCounter

            }


      }

      override fun onResume() {
            super.onResume()
            readTheList()
            adpter = ArrayAdapter(this, android.R.layout.simple_list_item_1, thkrList)
            listView?.adapter = adpter
      }

      fun readTheList(){
            list = data.readMyDataBs()
            thkrList = data.finedRow()
            if (thkrList.size ==0){
                  data.insertRow("سبحان الله",34)
                  data.insertRow("الحمد لله",33)
                  data.insertRow("الله أكبر",33)
                  data.insertRow("اللهم صل على محمد ,ال محمد",100)
                  data.insertRow("لا الله الا الله",100)
                  list = data.readMyDataBs()
                  thkrList = data.finedRow()
            }
      }
}