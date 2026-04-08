package com.example.financemanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financemanager.data.Transaction
import com.example.financemanager.repository.TransactionRepository
import kotlinx.coroutines.launch


class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TransactionRepository(application)

    val allTransactions = repository.allTransactions
    val recentTransactions = repository.recentTransactions
    val totalIncome = repository.totalIncome
    val totalExpense = repository.totalExpense

    fun insert(transaction: Transaction) {

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