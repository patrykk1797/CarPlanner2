package com.example.carplanner.ui.wydatki

import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.carplanner.MyAppDatabaseHelper
import com.example.carplanner.R
import com.example.carplanner.SingleItemActivity

class ListItemWydatki : AppCompatActivity(){

    var cursor: Cursor? = null;
    var db: MyAppDatabaseHelper? = null;


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_wydatki)



        loadDataCursorAdapter()

        findViewById<ListView>(R.id.listView1).setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SingleItemActivity::class.java)
            Toast.makeText(this,"id " + id, Toast.LENGTH_SHORT).show()
            intent.putExtra("id", id)
            startActivity(intent)
        }


        findViewById<EditText>(R.id.editTextFilterName).doOnTextChanged { text, start, before, count ->
            loadDataCursorAdapterWithName(text.toString())
        }


    }



    @RequiresApi(Build.VERSION_CODES.P)
    fun loadDataCursorAdapter(){
        val context = this
        db = MyAppDatabaseHelper(context)
        cursor = db!!.queryWithBuilder(arrayOf(MyAppDatabaseHelper.COL_ID, MyAppDatabaseHelper.COL_NAME), null, null, MyAppDatabaseHelper.COL_PRICE, MyAppDatabaseHelper.COL_DATA)


        var adatper =  SimpleCursorAdapter(this,
                R.layout.list_one_value,
                cursor,
                arrayOf(MyAppDatabaseHelper.COL_NAME),
                intArrayOf(R.id.textView2), 0)
        val listView = findViewById<ListView>(R.id.listView1)
        listView.adapter=adatper
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun loadDataCursorAdapterWithName( name: String?){
        val context = this
        db = MyAppDatabaseHelper(context)
        if(name == null || name.isNullOrBlank()) {
            cursor = db!!.queryWithBuilder(
                    arrayOf(
                            MyAppDatabaseHelper.COL_ID,
                            MyAppDatabaseHelper.COL_NAME
                    ), null, null, MyAppDatabaseHelper.COL_PRICE, MyAppDatabaseHelper.COL_DATA
            )
        }else{
            cursor = db!!.queryWithBuilder(
                    arrayOf(
                            MyAppDatabaseHelper.COL_ID,
                            MyAppDatabaseHelper.COL_NAME
                    ), "name like ?", arrayOf("%"+name+"%"), MyAppDatabaseHelper.COL_PRICE, MyAppDatabaseHelper.COL_DATA
            )

        }


        var adatper =  SimpleCursorAdapter(this,
                R.layout.list_one_value,
                cursor,
                arrayOf(MyAppDatabaseHelper.COL_NAME),
                intArrayOf(R.id.textView2), 0)
        val listView = findViewById<ListView>(R.id.listView1)
        listView.adapter=adatper
    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun loadData(){
        val context = this
        val db = MyAppDatabaseHelper(context)
        cursor = db.queryWithBuilder(arrayOf(MyAppDatabaseHelper.COL_NAME), null, null, MyAppDatabaseHelper.COL_PRICE, MyAppDatabaseHelper.COL_DATA)

        if(cursor!!.moveToFirst()){
            var data=""
            do{
                // data +=cursor.getString(cursor.getColumnIndex(MyAppDatabaseHelper.COL_NAME)) + "\n"

                data += cursor!!.getString(0) + "\n"

            }while (cursor!!.moveToNext())

            Toast.makeText(this,data, Toast.LENGTH_LONG).show()
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
        db!!.close()
    }
}
