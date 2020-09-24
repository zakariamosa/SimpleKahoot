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
import java.time.LocalDate

var allQuizes= mutableListOf<Quiz>()
var allTeachers= mutableListOf<Teacher>()
lateinit var currentTeacher:Teacher
lateinit var currentStudent:Student
var allStudents= mutableListOf<Student>()
var allTransactions= mutableListOf<Transaction>()
var allTransactionDetails= mutableListOf<TransactionDetails>()

class MainActivity : AppCompatActivity() {




    init {
        currentTeacher= Teacher("David","zakaria.mosa@iths.se","123456")
        allTeachers.add(currentTeacher)
        var myquestions= mutableListOf<Question>()
        myquestions.add(Question("133 % 10 = ","0","5", "3", "7",3))
        myquestions.add(Question("Var ligger strings localization i android ? ","java","manifest", "layout", "values",4))
        myquestions.add(Question("vad gör man för att jobba gemensam med Git på olika ställe","pull .... add .....commit...push","add...commit...push", "commit...push", "push",1))
        myquestions.add(Question("1003 X 964 = ","986892","966892", "976892", "996892",2))
        myquestions.add(Question("15 - (3*3) / 6 = ","0","1", "2", "3",2))
        var quz= if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Quiz(currentTeacher,5,10,"abcd1234", "iths20", LocalDate.now(),questions = myquestions)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        allQuizes.add(quz)
}


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