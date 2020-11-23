package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDetailsDao {

    @Insert
    fun insertTransactionDetails(transactionDetails:TransactionDetails)

    @Query("SELECT * FROM TRANSACTIONDETAILS WHERE quizCode like :quizCode AND StudentName NOT LIKE :StudentName")
    fun gettransactiondetailstotheotherstudents(quizCode:String, StudentName:String):List<TransactionDetails>

    @Query("SELECT * FROM TRANSACTIONDETAILS WHERE quizCode like :quizCode AND questionId LIKE :questionId")
    fun gettransactiondetailstoAllstudents(quizCode:String,questionId:Int):List<TransactionDetails>

    @Query("SELECT * FROM TRANSACTIONDETAILS WHERE quizCode like :quizCode AND transactionId LIKE :transactionId")
    fun gettransactiondetailstothecurrenttransaction(quizCode:String, transactionId:Int):List<TransactionDetails>
}