package com.example.carplanner.ui.wydatki

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


class WydatkiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Wydatki na twój samochód"
    }
    val text: LiveData<String> = _text
}