package com.example.carplanner

import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi

class SingleItemActivity : AppCompatActivity() {

    var cursor: Cursor? = null
    @RequiresApi(Build.VERSION_CODES.P)
    var db : MyAppDatabaseHelper? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_item)


        var id = intent.extras!!.get("id")
        db = MyAppDatabaseHelper(this)
        cursor = db!!.queryWithBuilder(arrayOf(MyAppDatabaseHelper.COL_ID, MyAppDatabaseHelper.COL_NAME, MyAppDatabaseHelper.COL_TYPE,
                MyAppDatabaseHelper.COL_PRICE, MyAppDatabaseHelper.COL_DATA,  MyAppDatabaseHelper.COL_DESC ),
                "_id = ?",
                arrayOf(id.toString()),
                null,
                MyAppDatabaseHelper.COL_DATA
        )

        if (cursor!!.moveToFirst()) {

            findViewById<TextView>(R.id.textViewItemId).setText(cursor!!.getString(0))
            findViewById<TextView>(R.id.textViewItemType).setText(cursor!!.getString(2))
            findViewById<TextView>(R.id.textViewItemName).setText(cursor!!.getString(1))
            findViewById<TextView>(R.id.textViewItemPrice).setText(cursor!!.getString(3))
            findViewById<TextView>(R.id.textViewItemData).setText(cursor!!.getString(4))
            findViewById<TextView>(R.id.textViewItemDsc).setText(cursor!!.getString(5))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
        db!!.close()
    }
}