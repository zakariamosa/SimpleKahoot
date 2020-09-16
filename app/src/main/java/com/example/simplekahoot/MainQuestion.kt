package com.example.simplekahoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainQuestion : AppCompatActivity() {


    var question = findViewById<EditText>(R.id.txtQuestion)
    var answer1 = findViewById<EditText>(R.id.txtAnswer1)
    var answer2 = findViewById<EditText>(R.id.txtAnswer2)
    var answer3 = findViewById<EditText>(R.id.txtAnswer3)
    var answer4 = findViewById<EditText>(R.id.txtAnswer4)
    var rad1 = findViewById<RadioButton>(R.id.rbAnswer1)
    var rad2 = findViewById<RadioButton>(R.id.rbAnswer2)
    var rad3 = findViewById<RadioButton>(R.id.rbAnswer3)
    var rad4 = findViewById<RadioButton>(R.id.rbAnswer4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_question)

        var questionNumber = intent.getIntExtra("questionNumber", 0)
        var designQuestionNumber = findViewById<TextView>(R.id.txtQuestionNumber)
        designQuestionNumber.text = "QuestionNumber: " + questionNumber.toString()
        var btnSaveQuestion = findViewById<Button>(R.id.btnSaveQuestion)

        btnSaveQuestion.setOnClickListener() {
            SaveCurrentQuestion()
        }
    }


    fun SaveCurrentQuestion() {
        var RightAnswer: Int = 0
        if (rad1.isChecked) {
            RightAnswer = 1
        }
        if (rad2.isChecked) {

            RightAnswer = 2

        }
        if (rad3.isChecked) {
            RightAnswer = 3
        }
        if (rad4.isChecked) {
            RightAnswer = 4
        }


        var q = Question(
            question.text.toString(),
            answer1.text.toString(),
            answer2.text.toString(),
            answer3.text.toString(),
            answer4.text.toString(),
            RightAnswer
        )
    }


}