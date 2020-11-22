package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TransactionDetailsDao {

    @Insert
    fun insertTransactionDetails(transactionDetails:TransactionDetails)
}