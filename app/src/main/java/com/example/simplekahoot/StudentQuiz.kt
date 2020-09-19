package com.example.simplekahoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StudentQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_quiz)



        val quzcode = intent.getStringExtra("QuizCode").toString()
        val studentQuestionFragment =  StudentQuestion.newInstance(quzcode,quzcode)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, studentQuestionFragment, "studentQuestionFragment" )

        transaction.commit()





    }
}