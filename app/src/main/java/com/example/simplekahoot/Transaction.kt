package com.example.simplekahoot

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true) var transactionId: Int,
    @Embedded var student:Student/*,
    @ColumnInfo(name = "thequizcode") var quizcode:String*/
)