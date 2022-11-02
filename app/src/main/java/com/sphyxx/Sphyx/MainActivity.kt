package com.sphyxx.Sphyx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clicklistener()
    }

    public fun clicklistener(){
        var calculator = findViewById<CardView>(R.id.calculator);
        var feedback = findViewById<CardView>(R.id.feedback);
        var notes = findViewById<CardView>(R.id.notes);
        var settings = findViewById<CardView>(R.id.settings);

        calculator.setOnClickListener {
            opencalculator()
        }

        feedback.setOnClickListener {
            openfeedback()
        }

        notes.setOnClickListener {
            opennotes()
        }

        settings.setOnClickListener {
            opensettings()
        }

    }
    public fun opencalculator(){
        startActivity(Intent(this@MainActivity,calculator::class.java))

    }
    public fun openfeedback(){
        startActivity(Intent(this@MainActivity,feedback::class.java))

    }
    public fun opennotes(){
        startActivity(Intent(this@MainActivity,notes::class.java))

    }
    public fun opensettings(){
        startActivity(Intent(this@MainActivity,settings::class.java))

    }
}