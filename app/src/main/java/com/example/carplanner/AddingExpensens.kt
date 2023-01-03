package com.example.carplanner

import android.app.Activity
import android.content.Intent
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.carplanner.ui.wydatki.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AddingExpensens : AppCompatActivity() {
    var db : MyAppDatabaseHelper? = null

    @RequiresApi(Build.VERSION_CODES.P)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_expensens)
        val context = this
        db = MyAppDatabaseHelper(context)

        //Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)



        //Button click to show DatePickerDialog
        val textdate: TextView = findViewById(R.id.edit_data_wydatki)
        val btn_date: Button  = findViewById(R.id.button_date_wydatki)
      btn_date.setOnClickListener{
          val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view:DatePicker, year, monthOfYear, dayOfMonth ->

              // Display Selected date in textbox
              textdate.setText("" + dayOfMonth + "/"+(monthOfYear+1) +"/"+ year)

          }, year, month, day)

          dpd.show()
      }

        val fab: FloatingActionButton = findViewById(R.id.back_btn_adding  )
        fab.setOnClickListener { view ->
            val intent = Intent(
                this, AddingActivity ::class.java)
            startActivity (intent)}

        findViewById<Button>(R.id.buttonSave_wydatek).setOnClickListener {
            var item = Item()
            item.name = findViewById<EditText>(R.id.edit_nazwa_wydatek).text.toString()
            item.dsc = findViewById<EditText>(R.id.edit_opis_wydatek).text.toString()
            item.type = findViewById<EditText>(R.id.edit_typ_wydatek).text.toString()
            item.date = findViewById<EditText>(R.id.edit_data_wydatki).text.toString()
            item.price =  findViewById<EditText>(R.id.edit_kwota_wydatek).text.toString().toFloatOrNull()

            var result = db!!.insertData(item)

            val resultIntent = Intent()
            resultIntent.putExtra("INSERT_RESULT",4)
            setResult(Activity.RESULT_OK, resultIntent);
            finish()

        }


}    override fun onDestroy() {
        super.onDestroy()
        db!!.close()
    }
}