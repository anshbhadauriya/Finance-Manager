package com.example.financemanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: String,
    val note: String,
    val date: Long,           // Store as milliseconds (easy to sort & format)
    val type: String          // "INCOME" or "EXPENSE"
)