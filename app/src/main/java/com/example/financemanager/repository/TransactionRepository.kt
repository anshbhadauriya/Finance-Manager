package com.example.financemanager.repository

import com.example.financemanager.data.AppDatabase
import com.example.financemanager.data.Transaction
import android.content.Context

// Repository is the clean bridge between ViewModel and Database.
// ViewModel never talks to the DB directly.
class TransactionRepository(context: Context) {

    private val dao = AppDatabase.getDatabase(context).transactionDao()

    val allTransactions = dao.getAllTransactions()
    val recentTransactions = dao.getRecentTransactions()
    val totalIncome = dao.getTotalIncome()
    val totalExpense = dao.getTotalExpense()

    suspend fun insert(transaction: Transaction) {
        dao.insert(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        dao.delete(transaction)
    }
}