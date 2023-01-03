package com.example.carplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)

        val button = findViewById<Button>(R.id.button_add_fuel)
        button.setOnClickListener {
            val intent = Intent(this, AddingFuel::class.java)
            startActivity(intent)}

            val button1 = findViewById<Button>(R.id.button_add_repair)
            button1.setOnClickListener {
                val intent = Intent(this, AddingRepairs::class.java)
                startActivity(intent)}
                val button2 = findViewById<Button>(R.id.button_add_expenses)
                button2.setOnClickListener {
                    val intent = Intent(this, AddingExpensens::class.java)
                    startActivity(intent)}
                    val button3 = findViewById<Button>(R.id.button_add_oc)
                    button3.setOnClickListener {
                        val intent = Intent(this, AddingOc::class.java)
                        startActivity(intent)}
                        val button4 = findViewById<Button>(R.id.button_add_pt)
                        button4.setOnClickListener {
                            val intent = Intent(this, AddingPt::class.java)
                            startActivity(intent)}


            val fab: FloatingActionButton = findViewById(R.id.back_btn_adding  )
            fab.setOnClickListener { view ->
                val intent = Intent(
                    this, MainActivity ::class.java)
                startActivity (intent)}
        }
    }