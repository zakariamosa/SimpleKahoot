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

class CurrentStudent : AppCompatActivity() {
    lateinit var txtstudentname:EditText
    lateinit var txterror:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_student)

        val quzcode= intent.getStringExtra("QuizCode").toString()

        var btncontinue=findViewById<Button>(R.id.btnStudentContinueToQuiz)
        txtstudentname=findViewById(R.id.txtCurrentStudent)
        txterror=findViewById(R.id.txterrorstudentname)

        btncontinue.setOnClickListener(){
            val transactionswithsamequizcode=allTransactions.filter {t-> t.quizcode.trim().equals(quzcode.trim())}
            val transactionswithsamequizcodeandname= transactionswithsamequizcode.filter {t-> t.student.StudentName.trim().equals(txtstudentname.text.toString().trim())}
            if (txtstudentname.text.toString().trim() == ""){
                txterror.text="Invalid Student Name"
                txterror.visibility= View.VISIBLE
            }
            else if (transactionswithsamequizcodeandname.isNotEmpty()){
                txterror.text="A user with the same name did the quiz before You have to enter a different name"
                txterror.visibility= View.VISIBLE
            }
            else
            {
                currentStudent= Student(txtstudentname.text.toString(),0.0)
                allStudents.add(currentStudent)
                allTransactions.add(Transaction(currentStudent,quzcode))
                //val intent=Intent(this, StudentQuizQuestion::class.java)

                val intent=Intent(this, StudentQuiz::class.java)

                intent.putExtra("QuizCode",quzcode)

                startActivity(intent)
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