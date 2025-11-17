package com.example.ca1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AdmissionFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admission_form)

        val toolbar = findViewById<Toolbar>(R.id.formToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Admission Form"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val departmentInput = findViewById<EditText>(R.id.departmentInput)
        val gpaInput = findViewById<EditText>(R.id.gpaInput)
        val submitButton = findViewById<Button>(R.id.submitBtn)
        val progressBar = findViewById<ProgressBar>(R.id.formProgressBar)

        progressBar.visibility = ProgressBar.GONE

        submitButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val department = departmentInput.text.toString().trim()
            val gpa = gpaInput.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || department.isEmpty() || gpa.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = ProgressBar.VISIBLE
                submitButton.isEnabled = false

                Thread {
                    Thread.sleep(2000)
                    runOnUiThread {
                        progressBar.visibility = ProgressBar.GONE
                        showCustomToast("Application Submitted Successfully!")

                        Thread {
                            Thread.sleep(1000)
                            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                                putExtra("applicantName", name)
                                putExtra("applicantEmail", email)
                                putExtra("applicantDepartment", department)
                            }
                            startActivity(intent)
                            finish()
                        }.start()
                    }
                }.start()
            }
        }
    }

    private fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
