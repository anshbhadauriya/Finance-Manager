package com.example.financemanager.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.example.financemanager.data.Transaction
import com.example.financemanager.databinding.FragmentAddTransactionBinding
import com.example.financemanager.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionFragment : Fragment() {

    private val viewModel: TransactionViewModel by activityViewModels()
    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private var selectedDateMillis = System.currentTimeMillis()
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    private val categories = listOf(
        "Food", "Transport", "Shopping", "Health",
        "Entertainment", "Salary", "Bills", "Education", "Other"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategoryDropdown()
        setupDatePicker()
        setupSaveButton()


        binding.etDate.setText(dateFormat.format(Date(selectedDateMillis)))
    }

    private fun setupCategoryDropdown() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.etCategory.setAdapter(adapter)
    }

    private fun setupDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            selectedDateMillis = selection
            binding.etDate.setText(dateFormat.format(Date(selection)))
        }


        binding.etDate.setOnClickListener {
            if (!datePicker.isAdded) {
                datePicker.show(parentFragmentManager, "date_picker")
            }
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            if (validateForm()) {
                saveTransaction()
            }
        }
    }

    private fun validateForm(): Boolean {
        val amountStr = binding.etAmount.text.toString().trim()
        val category = binding.etCategory.text.toString().trim()

        if (amountStr.isEmpty()) {
            binding.etAmount.error = "Amount is required"
            binding.etAmount.requestFocus()
            return false
        }

        val amount = amountStr.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            binding.etAmount.error = "Enter a valid amount"
            binding.etAmount.requestFocus()
            return false
        }

        if (category.isEmpty()) {
            binding.etCategory.error = "Category is required"
            binding.etCategory.requestFocus()
            return false
        }

        return true
    }

    private fun saveTransaction() {

        val type = if (binding.toggleType.checkedButtonId == binding.btnIncome.id) "INCOME" else "EXPENSE"

        val transaction = Transaction(
            amount = binding.etAmount.text.toString().toDouble(),
            category = binding.etCategory.text.toString().trim(),
            note = binding.etNote.text.toString().trim(),
            date = selectedDateMillis,
            type = type
        )

        viewModel.insert(transaction)
        Toast.makeText(requireContext(), "Transaction saved!", Toast.LENGTH_SHORT).show()
        clearForm()
    }

    private fun clearForm() {
        binding.etAmount.text?.clear()
        binding.etCategory.text?.clear()
        binding.etNote.text?.clear()
        binding.etDate.setText(dateFormat.format(Date(System.currentTimeMillis())))
        binding.toggleType.check(binding.btnExpense.id)
        selectedDateMillis = System.currentTimeMillis()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}