package com.example.financemanager.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.financemanager.databinding.FragmentSummaryBinding
import com.example.financemanager.viewmodel.TransactionViewModel

class SummaryFragment : Fragment() {

    private val viewModel: TransactionViewModel by activityViewModels()
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel.totalIncome.observe(viewLifecycleOwner) { income ->
            binding.tvTotalIncome.text = "₹%.2f".format(income)
            updateNetBalance()
        }

        viewModel.totalExpense.observe(viewLifecycleOwner) { expense ->
            binding.tvTotalExpense.text = "₹%.2f".format(expense)
            updateNetBalance()
        }
    }

    private fun updateNetBalance() {
        val income = viewModel.totalIncome.value ?: 0.0
        val expense = viewModel.totalExpense.value ?: 0.0
        val balance = income - expense
        binding.tvNetBalance.text = "₹%.2f".format(balance)

        // Color the balance green/red based on positive/negative
        val color = if (balance >= 0)
            requireContext().getColor(com.example.financemanager.R.color.accent_green)
        else
            requireContext().getColor(com.example.financemanager.R.color.accent_red)

        binding.tvNetBalance.setTextColor(color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}