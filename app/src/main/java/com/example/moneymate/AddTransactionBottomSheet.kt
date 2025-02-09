package com.example.moneymate

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionBottomSheet : BottomSheetDialogFragment() {

    private lateinit var etAmount: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDate: EditText
    private lateinit var etNote: EditText
    private lateinit var etWallet: EditText
    private lateinit var btnSave: Button
    private lateinit var rbIncome: RadioButton
    private lateinit var rbExpense: RadioButton
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme) // Gunakan style fullscreen
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.form_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind Views
        etAmount = view.findViewById(R.id.et_amount)
        etCategory = view.findViewById(R.id.et_category)
        etDate = view.findViewById(R.id.et_date)
        etNote = view.findViewById(R.id.et_note)
        etWallet = view.findViewById(R.id.et_wallet)
        btnSave = view.findViewById(R.id.btn_save_transaction)
        rbIncome = view.findViewById(R.id.rb_income)
        rbExpense = view.findViewById(R.id.rb_expense)

        // Event Listeners
        etDate.setOnClickListener { showDatePicker() }
        etCategory.setOnClickListener { showCategorySelection() }
        etWallet.setOnClickListener { showWalletSelection() }
        btnSave.setOnClickListener { saveTransaction() }

        // Pastikan BottomSheet terbuka sepenuhnya
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val parent = view.parent as View
            val behavior = BottomSheetBehavior.from(parent)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(year, month, day)
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                etDate.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showCategorySelection() {
        val categories = arrayOf("Food", "Transport", "Shopping", "Entertainment", "Bills")
        AlertDialog.Builder(requireContext())
            .setTitle("Select Category")
            .setItems(categories) { _, which ->
                etCategory.setText(categories[which])
            }
            .show()
    }

    private fun showWalletSelection() {
        val wallets = arrayOf("Cash", "Bank", "Credit Card")
        AlertDialog.Builder(requireContext())
            .setTitle("Select Wallet")
            .setItems(wallets) { _, which ->
                etWallet.setText(wallets[which])
            }
            .show()
    }

    private fun saveTransaction() {
        val amount = etAmount.text.toString()
        val category = etCategory.text.toString()
        val date = etDate.text.toString()
        val note = etNote.text.toString()
        val wallet = etWallet.text.toString()
        val type = if (rbIncome.isChecked) "Income" else "Expense"

        if (amount.isEmpty() || category.isEmpty() || date.isEmpty() || wallet.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "$type: $amount saved!", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}
