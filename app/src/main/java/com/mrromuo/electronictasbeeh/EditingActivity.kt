package com.mrromuo.electronictasbeeh

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mrromuo.electronictasbeeh.MainActivity.Companion.POSITION
import com.mrromuo.electronictasbeeh.MainActivity.Companion.list

class EditingActivity : AppCompatActivity() {
      var position = 0
      var edDkr: EditText? = null
      var rdNun: EditText? = null
      var Dr: Adkar? = null
      var cancelButton: Button? = null
      var addButton: Button? = null
      var EditButton: Button? = null
      var NextButton: ImageButton? = null
      var OkButton: Button? = null
      var DeleteBut:ImageButton? =null
      val editableList = list
      lateinit var data: DataHelper
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_editing)
            data = DataHelper(this)
            edDkr = findViewById(R.id.EditDekerEdit)
            rdNun = findViewById(R.id.EditCountEdit)
            cancelButton = findViewById(R.id.EditCancelBUT)
            addButton = findViewById(R.id.EditAddBut)
            EditButton = findViewById(R.id.EditEditBut)
            NextButton = findViewById(R.id.EditNextBut)
            OkButton = findViewById(R.id.EditOkBut)
            DeleteBut = findViewById(R.id.EditDeleteBut)
            position = intent.getIntExtra(POSITION, 0)
            Dr = editableList[position]
            changeVal(Dr!!)
            cancelButton?.setOnClickListener() {
                  finish()
            }

            NextButton?.setOnClickListener() {
                  nextposition()
            }

            addButton?.setOnClickListener() {
                  if (edDkr?.text!!.isNotEmpty() && edDkr?.text!!.isNotBlank() && rdNun?.text!!.isNotBlank() && rdNun?.text!!.isNotEmpty())
                  {
                        val Dkr = edDkr?.text.toString()
                        val times = rdNun?.text.toString()
                        val lastmemis = editableList.size - 1
                        val lastmem = editableList[lastmemis]
                        val id = lastmem.Id + 1

                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.addalarttitle)
                              .setMessage(R.string.areyoushowr)
                              .setNegativeButton(android.R.string.cancel) { a, b ->

                              }
                              .setPositiveButton(android.R.string.ok) { a, b ->
                                    addToEditList(Dkr, times, id)
                              }
                        builder.show()
                  } else {
                        worningForEmpty()
                  }
            }

            EditButton?.setOnClickListener() {
                  if (edDkr?.text!!.isNotEmpty() && edDkr?.text!!.isNotBlank() && rdNun?.text!!.isNotBlank() && rdNun?.text!!.isNotEmpty())
                  {
                        val Dkr = edDkr?.text.toString()
                        val times = rdNun?.text.toString()
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(R.string.editalarttitle)
                              .setMessage(R.string.areyoushowredit)
                              .setNegativeButton(android.R.string.cancel) { a, b ->

                              }
                              .setPositiveButton(android.R.string.ok) { a, b ->
                                    editdatalist(Dkr, times)
                              }
                        builder.show()
                  } else {
                        worningForEmpty()
                  }

            }

            OkButton?.setOnClickListener() {
                  val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                  builder.setIcon(R.mipmap.ic_alart2)
                        .setMessage(R.string.okmessage)
                        .setTitle(R.string.addworning)
                        .setNegativeButton(android.R.string.cancel){a,b ->

                        }
                        .setPositiveButton(R.string.contnuo){a,b->
                              updataDataBase()
                        }
                        .show()
            }

            DeleteBut?.setOnClickListener(){
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
            Dr = editableList[position]
            changeVal(Dr!!)

      }

      private fun updataDataBase() {
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
            Dr = editableList[position]
            changeVal(Dr!!)
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

}