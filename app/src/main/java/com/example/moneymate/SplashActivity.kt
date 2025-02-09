package com.example.moneymate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Build
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import android.view.Window
import android.view.WindowManager


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.statusBarColor = ContextCompat.getColor(this, R.color.cream)
        }
        // Layout utama untuk Splash Screen
        val layout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            setBackgroundColor(ContextCompat.getColor(this@SplashActivity, R.color.cream))
        }

        // ImageView untuk menampilkan logo
        val splashImage = ImageView(this).apply {
            setImageResource(R.drawable.wallet)
            scaleType = ImageView.ScaleType.FIT_CENTER

            // Atur ukuran gambar (200dp x 200dp)
            layoutParams = LinearLayout.LayoutParams(400, 400)
        }

        // Tambahkan ImageView ke dalam Layout
        layout.addView(splashImage)
        setContentView(layout)

        // Handler untuk pindah ke MainActivity setelah delay 2 detik
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
