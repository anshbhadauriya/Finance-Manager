package com.example.financemanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financemanager.databinding.FragmentHomeBinding
import com.example.financemanager.viewmodel.TransactionViewModel

class HomeFragment : Fragment() {

    // activityViewModels() shares ONE ViewModel across all fragments
    private val viewModel: TransactionViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        val adapter = TransactionAdapter { transaction ->
            // Long press = show delete confirmation
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Transaction")
                .setMessage("Delete this ${transaction.category} entry of ₹${transaction.amount}?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(transaction) }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.recyclerTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        viewModel.recentTransactions.observe(viewLifecycleOwner) { transactions ->
            adapter.submitList(transactions)
            binding.tvEmpty.visibility = if (transactions.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun observeData() {
        // Observe income
        viewModel.totalIncome.observe(viewLifecycleOwner) { income ->
            binding.tvIncomeSmall.text = "₹%.0f".format(income)
            updateBalance()
        }

        // Observe expense
        viewModel.totalExpense.observe(viewLifecycleOwner) { expense ->
            binding.tvExpenseSmall.text = "₹%.0f".format(expense)
            updateBalance()
        }
    }

    private fun updateBalance() {
        val income = viewModel.totalIncome.value ?: 0.0
        val expense = viewModel.totalExpense.value ?: 0.0
        val balance = income - expense
        binding.tvBalance.text = "₹%.2f".format(balance)
    }

    // IMPORTANT: Always null the binding to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}