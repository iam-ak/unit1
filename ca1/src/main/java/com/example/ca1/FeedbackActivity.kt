package com.example.ca1

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val toolbar = findViewById<Toolbar>(R.id.feedbackToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Rate Your Experience"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val ratingText = findViewById<TextView>(R.id.ratingText)
        val submitButton = findViewById<Button>(R.id.submitFeedbackBtn)

        ratingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                ratingText.text = "Your Rating: $rating / 5 Stars"
            }
        }

        submitButton.setOnClickListener {
            val rating = ratingBar.rating
            if (rating > 0) {
                Toast.makeText(
                    this,
                    "Thank you for rating us $rating stars!",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            } else {
                Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
