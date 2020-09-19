package com.example.simplekahoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StudentQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_quiz)




        callStudentQuestion()




    }

    fun callStudentQuestion(){
        val quzcode = intent.getStringExtra("QuizCode").toString()
        val studentQuestionFragment =  StudentQuestion.newInstance(quzcode,"1")

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, studentQuestionFragment, "studentQuestionFragment" )

        transaction.commit()
    }


    fun replaceWithRightAnswerFragment() {
        val fragment = FragmentRightAnswer()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, "rightAnswerFragment")
        transaction.commit()


    }
    fun addRightAnswerFragment() {
        val fragment = FragmentRightAnswer()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment, "rightAnswerFragment" )
        transaction.commit()
    }



}