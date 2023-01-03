package com.example.carplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddingPt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_pt)
        val fab: FloatingActionButton = findViewById(R.id.back_btn_adding  )
        fab.setOnClickListener { view ->
            val intent = Intent(
                this, AddingActivity ::class.java)
            startActivity (intent)}
    }
}