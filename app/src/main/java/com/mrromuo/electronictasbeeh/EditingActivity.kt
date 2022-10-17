package com.mrromuo.electronictasbeeh

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.mrromuo.electronictasbeeh.MainActivity.Companion.POSITION
import com.mrromuo.electronictasbeeh.MainActivity.Companion.list

class EditingActivity : AppCompatActivity() {
      private var position = 0
      private var edDkr: EditText? = null
      private var rdNun: EditText? = null
      private var adkar: Adkar? = null
      private var cancelButton: Button? = null
      private var addButton: Button? = null
      private var editButton: Button? = null
      private var nextButton: ImageButton? = null
      private var OkButton: Button? = null
      private var deleteBut:ImageButton? =null
      private val editableList = list
      lateinit var data: DataHelper

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_editing)
            setSupportActionBar(findViewById(R.id.editToolBar))
            data = DataHelper(this)
            edDkr = findViewById(R.id.EditDekerEdit)
            rdNun = findViewById(R.id.EditCountEdit)
            cancelButton = findViewById(R.id.EditCancelBUT)
            addButton = findViewById(R.id.EditAddBut)
            editButton = findViewById(R.id.EditEditBut)
            nextButton = findViewById(R.id.EditNextBut)
            OkButton = findViewById(R.id.EditOkBut)
            deleteBut = findViewById(R.id.EditDeleteBut)
            position = intent.getIntExtra(POSITION, 0)
            adkar = editableList[position]
            changeVal(adkar!!)
            cancelButton?.setOnClickListener {
                  finish()
            }

            nextButton?.setOnClickListener {
                  nextposition()
            }

            addButton?.setOnClickListener {
                  if (edDkr?.text!!.isNotEmpty() && edDkr?.text!!.isNotBlank() && rdNun?.text!!.isNotBlank() && rdNun?.text!!.isNotEmpty())
                  {
                        val dkr = edDkr?.text.toString()
                        val times = rdNun?.text.toString()
                        val lastnames = editableList.size - 1
                        val lastmem = editableList[lastnames]
                        val id = lastmem.Id + 1

                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.addalarttitle)
                              .setMessage(R.string.areyoushowr)
                              .setNegativeButton(android.R.string.cancel) { a, b ->

                              }
                              .setPositiveButton(android.R.string.ok) { a, b ->
                                    addToEditList(dkr, times, id)
                              }
                        builder.show()
                  } else {
                        worningForEmpty()
                  }
            }

            editButton?.setOnClickListener {
                  if (edDkr?.text!!.isNotEmpty() && edDkr?.text!!.isNotBlank() && rdNun?.text!!.isNotBlank() && rdNun?.text!!.isNotEmpty())
                  {
                        val deeker = edDkr?.text.toString()
                        val times = rdNun?.text.toString()
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.editalarttitle)
                              .setMessage(R.string.areyoushowredit)
                              .setNegativeButton(android.R.string.cancel) { a, b ->

                              }
                              .setPositiveButton(android.R.string.ok) { a, b ->
                                    editdatalist(deeker, times)
                              }
                        builder.show()
                  } else {
                        worningForEmpty()
                  }

            }

            OkButton?.setOnClickListener {
                  val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                  builder.setIcon(R.mipmap.ic_alart2)
                        .setMessage(R.string.okmessage)
                        .setTitle(R.string.addworning)
                        .setNegativeButton(android.R.string.cancel){a,b ->

                        }
                        .setPositiveButton(R.string.contnuo){a,b->
                              upDataDataBase()
                        }
                        .show()
            }

            deleteBut?.setOnClickListener {
                  val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                  builder.setIcon(R.mipmap.ic_alart2)
                        .setMessage(R.string.delettextmessage)
                        .setTitle(R.string.deleteworning)
                        .setNegativeButton(android.R.string.cancel){a,b ->
                        }
                        .setPositiveButton(R.string.contnuo){a,b->
                              deletetext()
                        }
                        .show()
            }

      }

      private fun deletetext() {
            val thisposition = editableList[position]
            editableList.remove(thisposition)
            adkar = editableList[position]
            changeVal(adkar!!)

      }

      private fun upDataDataBase() {
            list = editableList
            data.DeleteAll()
            for (item in editableList) {
                  val atext = item.Deker
                  val anum = item.Count
                  data.insertRow(atext, anum)
            }

            finish()
      }

      private fun editdatalist(Dkr: String, times: String) {
            val thisDeker = editableList[position]
            thisDeker.Deker = Dkr
            thisDeker.Count = times.toInt()
            editableList[position] = thisDeker

      }

      private fun addToEditList(dkr: String, times: String, id: Int) {
            val newtime = times.toInt()
            val newDekeris: Adkar = Adkar(id, dkr, newtime)
            editableList.add(newDekeris)
      }

      fun changeVal(valu: Adkar) {
            edDkr?.setText(valu.Deker)
            rdNun?.setText(valu.Count.toString())

      }

      fun nextposition() {
            position = if (position < editableList.size - 1) ++position else 0
            adkar = editableList[position]
            changeVal(adkar!!)
      }

      fun worningForEmpty() {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setIcon(R.mipmap.ic_alart2)
                  .setMessage(R.string.emptyworninig)
                  .setTitle(R.string.itsemptyworning)
                  .setNegativeButton(android.R.string.ok) { a, b ->

                  }
                  .show()
     }

      override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.editmenu, menu)
            return true
      }

      override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val intent: Intent = when (item.itemId)
            {
                  R.id.Echangebackground-> Intent(this, BackgroundsController::class.java)
                  R.id.EMain -> Intent(this, MainActivity::class.java)
                  R.id.Ehelp -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 1)
                  R.id.Epolsy -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 2)
                  else -> Intent(this, Help::class.java).putExtra(MainActivity.KEY_HELP, 1)
            }
            startActivity(intent)
            return super.onOptionsItemSelected(item)
      }

}