package com.mrromuo.electronictasbeeh

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
      lateinit var sheardata:SharedPreferences
      lateinit var edtor:SharedPreferences.Editor
      lateinit var data :DataHelper
      lateinit var adpter:ArrayAdapter<String>

      companion object{
             var list:ArrayList<Adkar> = ArrayList()
             var thkrList:ArrayList<String> = ArrayList()
             var thkrCounter = 0
             var setCounter = 34
            const val POSITION = "POSITION"
            const val LastState = "DATASETS"
            const val KEY_COUNTERVALU = "THECOUNTERVALU"
            const val KEY_SET = "SETCOUNTER"
            const val KEY_POSITION = "POSITION"
      }

      @RequiresApi(Build.VERSION_CODES.S)
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            list = DataHelper(this).readMyDataBs()
            listView = findViewById(R.id.selectingList)
            textView = findViewById(R.id.textView)
            thekerTx = findViewById(R.id.thekeerTV)
            seqbar = findViewById(R.id.progressBar)
            button = findViewById(R.id.button)
            sheardata = getSharedPreferences(LastState, MODE_PRIVATE)


            edtor =sheardata.edit()
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

                  val counterValue = list[position]
                  val text = "${counterValue.Deker}  ${counterValue.Count}"
                  setCounter = counterValue.Count
                  thekerTx?.text = text
                  thkrCounter = 0
                  textView?.text = thkrCounter.toString()
                  seqbar?.max = setCounter
                  seqbar?.progress = thkrCounter
                  edtor.putInt(KEY_SET, setCounter)
                  edtor.putInt(KEY_POSITION,position)
                  edtor.commit()

            }
            //                     ==========
            listView?.setOnItemLongClickListener { parent, view, position, id ->
                 Toast.makeText(this, "Position long Clicked:"+" "+position,Toast.LENGTH_SHORT).show()
                  val intent=Intent(this@MainActivity,EditingActivity::class.java)
                  intent.putExtra(POSITION,position)

                  startActivity(intent)

                  return@setOnItemLongClickListener(true)
            }
            //                         ==========
            button?.setOnClickListener(){
                  if (thkrCounter < setCounter) {
                        ++thkrCounter
                        textView?.text = thkrCounter.toString()
                        seqbar?.progress= thkrCounter
                        edtor.putInt(KEY_COUNTERVALU, thkrCounter)
                        edtor.commit()
                  }
                  else
                  {
                        vibratorManager.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE)) }

            }


      }

      override fun onResume() {
            super.onResume()
            readTheList()
            adpter = ArrayAdapter(this, android.R.layout.simple_list_item_1, thkrList)
            listView?.adapter = adpter

            thkrCounter = sheardata.getInt(KEY_COUNTERVALU,0)
            val lastposition = sheardata.getInt(KEY_POSITION,0)
            setCounter = sheardata.getInt(KEY_SET,34)
            if (list.size >0)
            {
                  val counterValue = list[lastposition]
                  val text = "${counterValue.Deker}  ${counterValue.Count}"
                  thekerTx?.text = text
                  textView?.text = thkrCounter.toString()
                  seqbar?.progress= thkrCounter
                  seqbar?.max = setCounter
            }
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