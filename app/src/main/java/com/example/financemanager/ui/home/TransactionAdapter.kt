package com.example.financemanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanager.data.Transaction
import com.example.financemanager.databinding.ItemTransactionBinding
import java.text.SimpleDateFormat
import java.util.*

// ListAdapter is smarter than basic RecyclerView.Adapter — it diffs lists automatically
class TransactionAdapter(
    private val onLongClick: (Transaction) -> Unit
) : ListAdapter<Transaction, TransactionAdapter.ViewHolder>(DiffCallback()) {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    // Category → emoji mapping for visual flair
    private val categoryEmoji = mapOf(
        "Food" to "🍔", "Transport" to "🚗", "Shopping" to "🛍️",
        "Health" to "💊", "Entertainment" to "🎬", "Salary" to "💼",
        "Bills" to "📃", "Education" to "📚", "Other" to "💰"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.tvCategory.text = transaction.category
            binding.tvNote.text = transaction.note.ifBlank { "No note" }
            binding.tvDate.text = dateFormat.format(Date(transaction.date))
            binding.tvCategoryIcon.text = categoryEmoji[transaction.category] ?: "💰"

            // Green for income, red for expense
            if (transaction.type == "INCOME") {
                binding.tvAmount.text = "+ ₹%.2f".format(transaction.amount)
                binding.tvAmount.setTextColor(
                    binding.root.context.getColor(com.example.financemanager.R.color.accent_green)
                )
            } else {
                binding.tvAmount.text = "- ₹%.2f".format(transaction.amount)
                binding.tvAmount.setTextColor(
                    binding.root.context.getColor(com.example.financemanager.R.color.accent_red)
                )
            }

            // Long press to delete
            binding.root.setOnLongClickListener {
                onLongClick(transaction)
                true
            }
        }
    }

    // DiffCallback tells RecyclerView exactly what changed — avoids full redraws
    class DiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(old: Transaction, new: Transaction) = old.id == new.id
        override fun areContentsTheSame(old: Transaction, new: Transaction) = old == new
    }
}