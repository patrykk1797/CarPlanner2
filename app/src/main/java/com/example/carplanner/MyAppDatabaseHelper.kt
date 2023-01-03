package com.example.carplanner

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.carplanner.ui.wydatki.Item




@RequiresApi(Build.VERSION_CODES.P)
class MyAppDatabaseHelper(
    var context: Context?
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "MenuItemsDB"
        val DB_VERSION = 3
        val TABLE_NAME = "Items"
        val COL_NAME = "name"
        val COL_TYPE = "type"
        val COL_DESC = "dsc"
        val COL_PRICE = "price"
        val COL_ID = "_id"
        val COL_DATA = "date"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_TYPE + " TEXT," +
                COL_DESC + " TEXT," +
                COL_DATA + " TEXT," +
                COL_PRICE + " NUMERIC )"
                db?.execSQL(createTable)

                insertInitialValues(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            val dropTable = "DROP table IF EXISTS " + TABLE_NAME
            db?.execSQL(dropTable)
             onCreate(db)

    }

    fun insertInitialValues( db: SQLiteDatabase?){

        val contentValues = ContentValues()
        contentValues.put(COL_NAME, "Pierwszy produkt")
        contentValues.put(COL_DESC, "Opis produktu")
        contentValues.put(COL_PRICE, 10.44)
        contentValues.put(COL_TYPE, "TEST")
        contentValues.put(COL_DATA, "7/2/2021")


        val result =  db?.insert(TABLE_NAME,null, contentValues) //DODANIE 1 wiersza
        /*Argument nullColumnHack typu String jest opcjonalny i w przeważającej większości
        przypadków nadajemy mu wartość null, tak jak zrobiliśmy to w powyższym przykładzie. Jest
        on używany w sytuacjach, gdy obiekt ContentValues jest pusty i trzeba zapisać w tabeli pusty
        rekord. Baza SQLite nie pozwala zapisywać pustych wierszy, jeśli nie została określona nazwa
        przynajmniej jednej kolumny; parametr nullColumnHack pozwala wskazać tę kolumnę.
        * */

        if (result == 0L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }


    fun readData(): MutableList<Item> {
        val list: MutableList<Item> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var item = Item()
                item.id = result.getInt(result.getColumnIndex(COL_ID))
                item.name = result.getString(result.getColumnIndex(COL_NAME))
                item.price = result.getFloat(result.getColumnIndex(COL_PRICE))
                item.type = result.getString(result.getColumnIndex(COL_TYPE))
                item.date = result.getString((result.getColumnIndex(COL_DATA)))
                item.dsc = result.getString(result.getColumnIndex(COL_DESC))

                list.add(item)
            }
            while (result.moveToNext())
        }
        return list
    }

    fun insertData(item: Item) : Long {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, item.name)
        contentValues.put(COL_DESC, item.dsc)
        contentValues.put(COL_PRICE, item.price)
        contentValues.put(COL_TYPE, item.type)
        contentValues.put(COL_DATA,item.date)
        val result = database.insert(TABLE_NAME, null, contentValues)

        return result;
    }








    fun  queryWithBuilder(columns: Array<String>?, filterColumns: String?, filterValues: Array<String>?, order: String?, colData: String) : Cursor{
        var builder = SQLiteQueryBuilder();
        builder.tables = TABLE_NAME
        val database = this.readableDatabase
        return builder.query(database,columns, filterColumns,filterValues,null,null, order)

    }

}