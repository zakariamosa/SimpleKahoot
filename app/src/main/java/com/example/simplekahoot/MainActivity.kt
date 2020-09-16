package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

var allQuizes= mutableListOf<Quiz>()
var allTeachers= mutableListOf<Teacher>()
lateinit var currentTeacher:Teacher

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnGetTheQuiz=findViewById<Button>(R.id.btnGetTheQuiz)
        var QuizCode=findViewById<EditText>(R.id.txtQuizCode)
        var QuizCodeNotFound=findViewById<TextView>(R.id.txtError)
        var btnTeacher=findViewById<Button>(R.id.btnTeacher)
        btnGetTheQuiz.setOnClickListener(){
            QuizCodeNotFound.visibility=View.INVISIBLE
            if (validQuizCode(QuizCode.text.toString())){
                goToCurrentStudentActivity(QuizCode.text.toString())
            }else{
                QuizCodeNotFound.text="Quiz Not Found"
                QuizCodeNotFound.visibility=View.VISIBLE
            }

        }
        btnTeacher.setOnClickListener(){
            goToTeacherLoginActivity()
        }
    }

    fun goToCurrentStudentActivity( QuizCode:String){
        var intent= Intent(this,CurrentStudent::class.java)
        intent.putExtra("QuizCode",QuizCode)
        startActivity(intent)
    }

    fun validQuizCode(QuizCode:String):Boolean{
        for (quiz in allQuizes){
            if (quiz.quizCode==QuizCode) {
                return true
            }
        }
        return false
    }

    fun goToTeacherLoginActivity(){
        var intent=Intent(this,TeacherLogin::class.java)
        startActivity(intent)
    }


}