package com.example.simplekahoot

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

class StudentQuizQuestion : AppCompatActivity() {

    lateinit var question: TextView
    lateinit var answer1: TextView
    lateinit var answer2: TextView
    lateinit var answer3: TextView
    lateinit var answer4: TextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_quiz_question)

        question = findViewById<TextView>(R.id.txtViewStudentQuestion)
        answer1 = findViewById<TextView>(R.id.txtStudentAnswer1)
        answer2 = findViewById<TextView>(R.id.txtStudentAnswer2)
        answer3 = findViewById<TextView>(R.id.txtStudentAnswer3)
        answer4 = findViewById<TextView>(R.id.txtStudentAnswer4)


        val quzcode = intent.getStringExtra("QuizCode").toString()

        var qustnmbr = findViewById<TextView>(R.id.txtQuestionNumberForStudent)
        var totalquestions: Int


        var quizquestions = allQuizes.filter { Quiz -> Quiz.quizCode == quzcode }
        totalquestions = quizquestions[0]?.numberOfQuestions
        qustnmbr.setText("${qustnmbr.hint}: 1/${totalquestions.toString()}")
        question.setText(quizquestions[0]?.questions?.get(0)?.question)
        answer1.setText(quizquestions[0]?.questions?.get(0)?.alternativeAnswer1)
        answer2.setText(quizquestions[0]?.questions?.get(0)?.alternativeAnswer2)
        answer3.setText(quizquestions[0]?.questions?.get(0)?.alternativeAnswer3)
        answer4.setText(quizquestions[0]?.questions?.get(0)?.alternativeAnswer4)


        var myprogressbar = findViewById<ProgressBar>(R.id.progBarTimer)
        myprogressbar.min = 0
        myprogressbar.max = 200
        myprogressbar.incrementProgressBy(200)
        val timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                myprogressbar.incrementProgressBy(-10)
            }

            override fun onFinish() {
                myprogressbar.setBackgroundColor(Color.RED)
                calculateScore(0,false)
            }
        }
        timer.start()


        answer1.setOnTouchListener() { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.GRAY)
                    myprogressbar.incrementProgressBy(0)
                    timer.cancel()
                    val isRightAnswer= quizquestions[0]?.questions?.get(0)?.rightAnswer==1
                    calculateScore(myprogressbar.progress,isRightAnswer)
                }

            }
            true

        }

        answer2.setOnTouchListener() { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.GRAY)
                    myprogressbar.incrementProgressBy(0)
                    timer.cancel()
                    val isRightAnswer= quizquestions[0]?.questions?.get(0)?.rightAnswer==2
                    calculateScore(myprogressbar.progress,isRightAnswer)
                }

            }
            true

        }

        answer3.setOnTouchListener() { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.GRAY)
                    myprogressbar.incrementProgressBy(0)
                    timer.cancel()
                    val isRightAnswer= quizquestions[0]?.questions?.get(0)?.rightAnswer==3
                    calculateScore(myprogressbar.progress,isRightAnswer)
                }

            }
            true

        }

        answer4.setOnTouchListener() { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.GRAY)
                    myprogressbar.incrementProgressBy(0)
                    timer.cancel()
                    val isRightAnswer= quizquestions[0]?.questions?.get(0)?.rightAnswer==4
                    calculateScore(myprogressbar.progress,isRightAnswer)
                }

            }
            true

        }

    }

    fun calculateScore(currentprog: Int,isRightAnswer:Boolean) {
        if (isRightAnswer){
            currentStudent.Score+=90.0
            currentStudent.Score+=currentprog/20
        }

        val txtscore = findViewById<TextView>(R.id.txtScore)
        txtscore.text = currentStudent.Score.toString()//currentprog.toString()
    }
}