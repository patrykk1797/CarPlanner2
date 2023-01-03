package com.example.carplanner.ui.wydatki
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.carplanner.R
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import com.example.carplanner.MyAppDatabaseHelper

import com.example.carplanner.SingleItemActivity

class WydatkiFragment : Fragment() {

    private lateinit var wydatkiViewModel: WydatkiViewModel
    var cursor: Cursor? = null;
    var db: MyAppDatabaseHelper? = null;
    var listView: ListView? = null;

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        wydatkiViewModel =
                ViewModelProvider(this).get(WydatkiViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wydatki, container, false)
        listView = root.findViewById<ListView>(R.id.listView1)


        loadDataCursorAdapter()

        root.findViewById<ListView>(R.id.listView1).setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, SingleItemActivity::class.java)
            Toast.makeText(activity, "id " + id, Toast.LENGTH_SHORT).show()
            intent.putExtra("id", id)
            startActivity(intent)

            root.findViewById<EditText>(R.id.editTextFilterName).doOnTextChanged { text, start, before, count ->
                loadDataCursorAdapterWithName(text.toString())
            }


        }


        return root



    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun loadDataCursorAdapter(){
        val context = this
        db = MyAppDatabaseHelper(activity)
        cursor = db!!.queryWithBuilder(arrayOf(MyAppDatabaseHelper.COL_ID, MyAppDatabaseHelper.COL_NAME),null,null, MyAppDatabaseHelper.COL_PRICE, MyAppDatabaseHelper.COL_DATA)


        var adatper =  SimpleCursorAdapter(activity,
                R.layout.list_one_value,
                cursor,
                arrayOf(MyAppDatabaseHelper.COL_NAME),
                intArrayOf(R.id.textView2), 0)

        listView!!.adapter=adatper

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun loadDataCursorAdapterWithName( name: String?){


        db = MyAppDatabaseHelper(activity)
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
        var adatper =  SimpleCursorAdapter(activity,
                R.layout.list_one_value,
                cursor,
                arrayOf(MyAppDatabaseHelper.COL_NAME),
                intArrayOf(R.id.textView2), 0)

        listView!!.adapter=adatper


    }
    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
        db!!.close()
    }
}
