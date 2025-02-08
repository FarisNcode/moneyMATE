package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        // Ambil tombol dari XML
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnSignup: Button = findViewById(R.id.btnSignup)

        // Efek fade-in
        btnLogin.alpha = 0f
        btnLogin.animate().alpha(1f).setDuration(1000)

        btnSignup.alpha = 0f
        btnSignup.animate().alpha(1f).setDuration(1500)

        // Arahkan ke LoginActivity
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Arahkan ke RegisterActivity
        btnSignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
