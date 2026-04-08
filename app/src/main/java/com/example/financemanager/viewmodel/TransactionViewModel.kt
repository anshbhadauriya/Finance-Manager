package com.example.financemanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.data.Transaction
import com.example.financemanager.repository.TransactionRepository
import kotlinx.coroutines.launch

// AndroidViewModel has access to Application context (needed for DB)
class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TransactionRepository(application)

    // These are observed by all 3 fragments
    val allTransactions = repository.allTransactions
    val recentTransactions = repository.recentTransactions
    val totalIncome = repository.totalIncome
    val totalExpense = repository.totalExpense

    fun insert(transaction: Transaction) {
        // viewModelScope auto-cancels when ViewModel is destroyed
        viewModelScope.launch {
            repository.insert(transaction)
        }
    }

    fun delete(transaction: Transaction) {
        viewModelScope.launch {
            repository.delete(transaction)
        }
    }
}