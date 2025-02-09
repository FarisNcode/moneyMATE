package com.example.moneymate

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.moneymate.data.User
import com.example.moneymate.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrasi)

        // Atur Status Bar Transparan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val tvLogin: TextView = findViewById(R.id.tvLogin)
        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Inisialisasi View
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etTelp = findViewById<EditText>(R.id.etTelp)
        val etNoRek = findViewById<EditText>(R.id.etNorek)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegis)

        // Inisialisasi Database
        val db = AppDatabase.getDatabase(this)
        val userDao = db.userDao()

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val telp = etTelp.text.toString().trim()
            val noRek = etNoRek.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Validasi Input
            if (username.isEmpty() || telp.isEmpty() || noRek.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Gunakan Coroutine untuk Database
            lifecycleScope.launch(Dispatchers.IO) {
                // Cek apakah username atau nomor rekening sudah ada
                val existingUser = userDao.getUserByUsername(username)
                if (existingUser != null) {
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Username sudah digunakan!", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                val existingRek = userDao.getUserByNoRek(noRek)
                if (existingRek != null) {
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Nomor rekening sudah digunakan!", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                // Hash Password
                val hashedPassword = hashPassword(password)

                // Simpan ke Database
                val newUser = User(
                    username = username,
                    telp = telp,
                    no_rek = noRek,
                    password = hashedPassword,
                    level = "user"
                )
                userDao.insertUser(newUser)

                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    // Fungsi Hash Password menggunakan SHA-256
    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}