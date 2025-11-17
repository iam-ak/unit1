package com.example.ca1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.homeToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "UniAdmission Portal"

        val applyNowButton = findViewById<Button>(R.id.applyNowBtn)
        val viewCoursesButton = findViewById<Button>(R.id.viewCoursesBtn)
        val rateUsButton = findViewById<Button>(R.id.rateUsBtn)

        applyNowButton.setOnClickListener {
            val intent = Intent(this, AdmissionFormActivity::class.java)
            startActivity(intent)
        }

        viewCoursesButton.setOnClickListener {
            Toast.makeText(this, "View Courses feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        rateUsButton.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }
    }
}
