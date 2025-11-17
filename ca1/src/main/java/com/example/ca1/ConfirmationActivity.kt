package com.example.ca1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val toolbar = findViewById<Toolbar>(R.id.confirmationToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Application Confirmation"

        val applicantName = intent.getStringExtra("applicantName") ?: "Applicant"
        val applicantEmail = intent.getStringExtra("applicantEmail") ?: ""
        val applicantDepartment = intent.getStringExtra("applicantDepartment") ?: ""

        val confirmationMessage = findViewById<TextView>(R.id.confirmationMessage)
        val detailsText = findViewById<TextView>(R.id.detailsText)
        val backButton = findViewById<Button>(R.id.backToHomeBtn)

        confirmationMessage.text = "Application Submitted Successfully, $applicantName!"
        detailsText.text = """
            Email: $applicantEmail
            Department: $applicantDepartment
            
            You will receive an email confirmation shortly. Please check your email for further instructions and your admission status.
        """.trimIndent()

        backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
