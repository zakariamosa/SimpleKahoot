package com.example.simplekahoot

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CurrentStudent : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    lateinit var txtstudentname:EditText
    lateinit var txterror:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_student)

        job = Job()
        db = AppDatabase.getInstance(this)

        val quzcode= intent.getStringExtra("QuizCode").toString()

        var btncontinue=findViewById<Button>(R.id.btnStudentContinueToQuiz)
        txtstudentname=findViewById(R.id.txtCurrentStudent)
        txterror=findViewById(R.id.txterrorstudentname)

        btncontinue.setOnClickListener(){



            val studentswithsamenameforthisquiz=async(Dispatchers.IO) {
                db.studentDao.getStudentByNameAndQuizCode(txtstudentname.text.trim().toString(),quzcode)
            }
            launch{
                if (studentswithsamenameforthisquiz.await().isEmpty()){
                    currentStudent= Student(0,txtstudentname.text.trim().toString(),quzcode)
                    launch(Dispatchers.IO){
                        db.studentDao.insertStudent(currentStudent)
                        val currentStudents=async(Dispatchers.IO) {
                            db.studentDao.getStudentByNameAndQuizCode(txtstudentname.text.trim().toString(),quzcode)
                        }
                        launch{
                            currentStudent=currentStudents.await()[0]
                            allStudents.add(currentStudent)
                            var newTransaction=Transaction(0,currentStudent/*,quzcode*/)

                            launch(Dispatchers.IO){
                                db.transactionDao.insertTransaction(newTransaction)
                                val transaction=async(Dispatchers.IO) {
                                    db.transactionDao.getTransactionAllDetailsByQuizCodeForAspecificStudent(currentStudent.quizCode,currentStudent.studentId)//zakkkkkkkk
                                }
                                launch {
                                    newTransaction.transactionId=transaction.await()[0].transaction.transactionId
                                    allTransactions.add(newTransaction)
                                    val intent=Intent(this@CurrentStudent, StudentQuiz::class.java)
                                    intent.putExtra("QuizCode",quzcode)
                                    intent.putExtra("transactionId",newTransaction.transactionId.toString())
                                    currenttransactionId=newTransaction.transactionId.toString()
                                    startActivity(intent)
                                }

                            }
                        }
                    }
                }else{
                    txterror.text="A user with the same name did the quiz before You have to enter a different name"
                    txterror.visibility= View.VISIBLE
                }
            }

        }
        txtstudentname.setOnTouchListener(){v,event->
            when(event?.action){
                MotionEvent.ACTION_UP->{
                    txterror.visibility= View.INVISIBLE
                }
            }
            false
        }
    }
}