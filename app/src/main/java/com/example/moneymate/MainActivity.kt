package com.example.moneymate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isIncomeForm = true // Default ke form Pendapatan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referensi elemen UI
        val checkExpensesButton: Button = findViewById(R.id.checkExpenses)
        val settingsIcon: ImageView = findViewById(R.id.settingsIcon)
        val formTitle: TextView = findViewById(R.id.formTitle)
        val editAmount: EditText = findViewById(R.id.editAmount)
        val editDescription: EditText = findViewById(R.id.editDescription)
        val saveButton: Button = findViewById(R.id.saveButton)
        val toggleFormButton: Button = findViewById(R.id.toggleFormButton)

        // Navigasi ke HistoryActivity
        checkExpensesButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke AccountActivity
        settingsIcon.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        // Toggle Form (Pendapatan <-> Pengeluaran)
        toggleFormButton.setOnClickListener {
            if (isIncomeForm) {
                // Beralih ke form Pengeluaran
                formTitle.text = "Tambah Pengeluaran"
                editAmount.hint = "Masukkan jumlah ..."
                editDescription.hint = "Keperluan pengeluaran ..."
                saveButton.text = "Simpan Pengeluaran"
                toggleFormButton.text = "Tambah Pendapatan"
            } else {
                // Beralih ke form Pendapatan
                formTitle.text = "Tambah Pendapatan"
                editAmount.hint = "Masukkan jumlah ..."
                editDescription.hint = "Sumber pendapatan ..."
                saveButton.text = "Simpan Pendapatan"
                toggleFormButton.text = "Tambah Pengeluaran"
            }
            isIncomeForm = !isIncomeForm
        }
    }
}
