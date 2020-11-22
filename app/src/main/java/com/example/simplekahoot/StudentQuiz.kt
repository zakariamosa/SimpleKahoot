package com.example.simplekahoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StudentQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setTheme(R.style.AppThemeFullScreen)
        setContentView(R.layout.activity_student_quiz)





        callStudentQuestion()




    }

    fun callStudentQuestion(){
        val quzcode = intent.getStringExtra("QuizCode").toString()
        val transactionId = intent.getStringExtra("transactionId").toString()
        val studentQuestionFragment =  StudentQuestion.newInstance(quzcode,"1",transactionId)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, studentQuestionFragment, "studentQuestionFragment" )

        transaction.commit()
    }






}