package com.example.financemanager.data

import androidx.lifecycle.LiveData
import androidx.room.*

// DAO = Data Access Object. All your SQL lives here.
@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    // Returns all transactions, newest first. LiveData auto-updates UI on changes.
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    // Recent 10 for Home screen
    @Query("SELECT * FROM transactions ORDER BY date DESC LIMIT 10")
    fun getRecentTransactions(): LiveData<List<Transaction>>

    // Sum of all income
    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 'INCOME'")
    fun getTotalIncome(): LiveData<Double>

    // Sum of all expenses
    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 'EXPENSE'")
    fun getTotalExpense(): LiveData<Double>
}