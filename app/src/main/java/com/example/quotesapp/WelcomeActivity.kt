package com.example.quotesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Reference to the "Enter" button in the layout
        val buttonEnter = findViewById<Button>(R.id.buttonEnter)

        // Set up a click listener for the Enter button
        buttonEnter.setOnClickListener {
            // Intent to navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}