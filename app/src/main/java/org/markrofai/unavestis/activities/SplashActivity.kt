package org.markrofai.unavestis.activities;

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.markrofai.unavestis.R

class SplashActivity : AppCompatActivity() {
    private val splashTime = 3000L // 3 seconds
    private lateinit var myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        myHandler = Handler()
        myHandler.postDelayed({
            goToMainActivity()
        }, splashTime)
    }

    private fun goToMainActivity() {
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
}
