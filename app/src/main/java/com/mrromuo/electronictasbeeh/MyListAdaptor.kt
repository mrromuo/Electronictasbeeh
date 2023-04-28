package com.mrromuo.electronictasbeeh

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.mrromuo.electronictasbeeh.MainActivity.Companion.POSITION


class MyListAdaptor (private val context: Activity,
                     private val deeker: ArrayList<String>
                     ): ArrayAdapter<String>(context, R.layout.my_list, deeker) {

      override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.my_list, null, true)

            // List items inflaters
            val textView = rowView.findViewById(R.id.textInsideList) as TextView
            val button = rowView.findViewById(R.id.localEditButton) as ImageButton
            // filling data in the list:
            textView.text = deeker[position]

            // Action of the button
            button.setOnClickListener(){
                  val intent = Intent(context, EditingActivity::class.java)
                  intent.putExtra(POSITION, position)
                  startActivity(context,intent,null)
            }
            return rowView
      }
}


