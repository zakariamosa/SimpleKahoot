package com.example.simplekahoot

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DateTimeException
import java.time.LocalDate
import java.util.*

@Entity(tableName = "quiz")
data class Quiz(
    @PrimaryKey(autoGenerate = true) val quizId: Int,
    @Embedded var QuizTeacher:Teacher,
    @ColumnInfo(name = "number of questions") var numberOfQuestions:Int,
    @ColumnInfo(name = "time for each question") var timeForEachQuestion:Int,
    var quizCode:String="",
    @ColumnInfo(name = "quiz name") var quizName:String,
    @ColumnInfo(name = "quiz date") var quizDate:String?=null/*,
    @ColumnInfo(name = "questions") var questions: MutableList<Question>?=null*/) {






    @RequiresApi(Build.VERSION_CODES.O)
    fun generateQuizCode(){

        quizCode= UUID.randomUUID().toString()

        quizDate= LocalDate.now().toString()
        QuizTeacher=currentTeacher
    }
}