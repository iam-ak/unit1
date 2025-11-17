package com.example.ca1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoText = findViewById<TextView>(R.id.splashLogo)
        val progressBar = findViewById<ProgressBar>(R.id.splashProgressBar)

        logoText.text = "UniAdmission\nPortal"
        progressBar.progress = 0

        Thread {
            for (i in 0..100 step 5) {
                progressBar.progress = i
                Thread.sleep(150)
            }
        }.start()

        // Navigate to Home after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
