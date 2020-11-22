package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaction:Transaction)

    @Query("SELECT * FROM `TRANSACTION` WHERE quizCode LIKE :quizCode AND studentId LIKE :studentId")
    fun getTransactionAllDetailsByQuizCodeForAspecificStudent(quizCode:String, studentId:Int):List<TransactionRelationWithTransactionDetails>
}