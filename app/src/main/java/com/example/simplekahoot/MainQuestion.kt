package com.example.simplekahoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_question)

        var questionNumber=intent.getIntExtra("questionNumber",0)
        var designQuestionNumber=findViewById<TextView>(R.id.txtQuestionNumber)
        designQuestionNumber.text="QuestionNumber: "+questionNumber.toString()
    }
}