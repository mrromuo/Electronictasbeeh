package com.mrromuo.electronictasbeeh

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.database.getStringOrNull

class DataHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
      companion object {
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "subha.db"
            private const val TABLE_CONTACTS = "deker"
            private const val KEY_ID = "id"
            private const val KEY_THEKER = "ALTHEKER"
            private const val KEY_TIMES = "TIMES"
            val charset = Charsets.UTF_8
      }

      override fun onCreate(db: SQLiteDatabase?) {
            val CREATE_CONTACTS_TABLE = ("CREATE TABLE $TABLE_CONTACTS " +
                    "( $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$KEY_THEKER VARCHAR," +
                    "$KEY_TIMES INTEGER)")
            db?.execSQL(CREATE_CONTACTS_TABLE)
      }

      override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
            onCreate(db)
      }

     @SuppressLint("Range", "Recycle")
      fun readMyDataBs():ArrayList<Adkar>{
            val list:ArrayList<Adkar> = ArrayList<Adkar>()
            val query ="SELECT * FROM $TABLE_CONTACTS;"
            val db = this.readableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
            var cursor: Cursor? = null
            try{
                  cursor = db.rawQuery(selectQuery, null)
            }catch (e: SQLiteException) {
                  db.execSQL(selectQuery)
                  return ArrayList()
            }
            // ==============================================
            var tId:Int
            var Dk:String
            var byteArray:ByteArray
            var Times:Int
            var reading:Adkar
            if (cursor.moveToFirst())
            {
                  do
                  {
                        tId = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                        //byteArray =  cursor.getString(cursor.getColumnIndex(KEY_THEKER))
//                        byteArray =  cursor.getString(cursor.getColumnIndex(KEY_THEKER)).toByteArray(
//                              )
//                        Dk = byteArray.toString(charset)
                        Dk =cursor.getString(cursor.getColumnIndex(KEY_THEKER))
                        Times = cursor.getInt(cursor.getColumnIndex(KEY_TIMES))
                        reading =Adkar(Id = tId,Deker =Dk,Count=Times)
                        list.add(reading)
                  } while (cursor.moveToNext())
            }
            return list
      }

      fun deleteRow(id:Int){
            val query = "DELETE FROM COMPANY WHERE $KEY_ID = $id;"
            val db = this.writableDatabase
            Log.i("insert() = ", query)
            db.execSQL(query)
      }

      fun insertRow(Theker:String, Times:Int){
            val db = this.writableDatabase
            val byteArray = Theker.toByteArray(charset)
            val query ="INSERT INTO $TABLE_CONTACTS ($KEY_THEKER, $KEY_TIMES) VALUES (" + "'"+Theker+"'"+" , "+"'"+Times+"');"
            Log.i("insert() = ", query)
            db.execSQL(query)
      }

      @SuppressLint("Range")
      fun finedRow():ArrayList<String>{
            val db = this.writableDatabase
            val list=ArrayList<String>()
            val query ="SELECT $KEY_THEKER FROM $TABLE_CONTACTS;"
            val cursor: Cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()){
                  do {
                        list.add(cursor.getString(cursor.getColumnIndex(KEY_THEKER)))
                  }while (cursor.moveToNext())
            }
            return list
      }

      fun DeleteAll(){
            val query="DELETE FROM $TABLE_CONTACTS;"
            val db = this.writableDatabase
            Log.i("insert() = ", query)
            db.execSQL(query)
      }
      fun changeRow(Deker:String,times:Int,id:Int){
            val db = this.writableDatabase
            db.execSQL("UPDATE $TABLE_CONTACTS SET $KEY_THEKER=$Deker $KEY_TIMES=$times WHERE $KEY_ID=$id ");
      }
}



