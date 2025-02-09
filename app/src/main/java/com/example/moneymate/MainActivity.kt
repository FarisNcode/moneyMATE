package com.example.moneymate

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.statusBarColor = ContextCompat.getColor(this, R.color.cream)
        }

        findViewById<FrameLayout>(R.id.nav_add).setOnClickListener {
            AddTransactionBottomSheet().show(supportFragmentManager, "AddTransaction")
        }
    }
}
