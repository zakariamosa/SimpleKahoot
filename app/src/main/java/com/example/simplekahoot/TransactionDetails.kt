package com.example.simplekahoot

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionDetails")
data class TransactionDetails(
    @PrimaryKey(autoGenerate = true) val transactionDetailsId: Long,
//    val quizcode:String,
    @Embedded val question:Question,
    val studentanswer:Int,
    @Embedded val student:Student,
    val studentcurrentscour:Double,
    val transactionId: Int
)