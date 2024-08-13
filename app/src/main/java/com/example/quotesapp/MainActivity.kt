package com.example.quotesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var selectedQuote: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // List of quotes with newline characters for formatting
        val quotes = listOf(
            "Believe in yourself\nand all that you are.",
            "Act as if what you do\nmakes a difference.\nIt does.",
            "Success is not how high\nyou have climbed,\nbut how you make a positive difference\nto the world.",
            "Your limitation\n—it’s only your imagination.",
            "Push yourself,\nbecause no one else is\ngoing to do it for you.",
            "Sometimes later\nbecomes never.\nDo it now.",
            "Great things never come\nfrom comfort zones.",
            "Dream it.\nWish it.\nDo it.",
            "Success doesn’t just find you.\nYou have to go out and get it.",
            "The harder you work\nfor something,\nthe greater you’ll feel\nwhen you achieve it."
        )

        val spinnerQuotes = findViewById<Spinner>(R.id.spinnerQuotes)
        val buttonShare = findViewById<Button>(R.id.buttonShare)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, quotes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuotes.adapter = adapter

        spinnerQuotes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedQuote = quotes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedQuote = quotes[0]
            }
        }

        buttonShare.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("quote", selectedQuote)
            startActivity(intent)
        }
    }
}