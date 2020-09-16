package com.example.simplekahoot

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*

class Quiz(var QuizTeacher:Teacher,var numberOfQuestions:Int, var timeForEachQuestion:Int,var quizCode:String="",var quizName:String,var quizDate:LocalDate?=null,var questions: MutableList<Question>?=null) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun generateQuizCode(){

        quizCode= UUID.randomUUID().toString()

        quizDate= LocalDate.now()
        QuizTeacher=currentTeacher
    }
}