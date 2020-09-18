package com.example.simplekahoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class StudentQuizQuestion : AppCompatActivity() {

    lateinit var question: TextView
    lateinit var answer1: TextView
    lateinit var answer2: TextView
    lateinit var answer3: TextView
    lateinit var answer4: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_quiz_question)

        question = findViewById<TextView>(R.id.txtViewStudentQuestion)
        answer1 = findViewById<TextView>(R.id.txtStudentAnswer1)
        answer2 = findViewById<TextView>(R.id.txtStudentAnswer2)
        answer3 = findViewById<TextView>(R.id.txtStudentAnswer3)
        answer4 = findViewById<TextView>(R.id.txtStudentAnswer4)



        val quzcode= intent.getStringExtra("QuizCode").toString()



        var quizquestions= allQuizes.filter { Quiz->Quiz.quizCode==quzcode }
        question.setText(quizquestions[0]?.questions?.get(0)?.question)

    }
}