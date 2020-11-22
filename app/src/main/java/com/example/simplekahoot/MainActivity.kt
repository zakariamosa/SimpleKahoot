package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.time.LocalDate
import kotlin.coroutines.CoroutineContext

var allQuizes= mutableListOf<Quiz>()
var allTeachers= mutableListOf<Teacher>()
lateinit var currentTeacher:Teacher
lateinit var currentStudent:Student
var allStudents= mutableListOf<Student>()
var allTransactions= mutableListOf<Transaction>()
var allTransactionDetails= mutableListOf<TransactionDetails>()
var currenttransactionId:String=""

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase






    init {
        currentTeacher= Teacher(0,"David","zakaria.mosa@iths.se","123456")
        allTeachers.add(currentTeacher)
        var myquestions= mutableListOf<Question>()
        myquestions.add(Question(0,"133 % 10 = ","0","5", "3", "7",3,0))
        myquestions.add(Question(0,"Var ligger strings localization i android ? ","java","manifest", "layout", "values",4,0))
        myquestions.add(Question(0,"vad gör man för att jobba gemensam med Git på olika ställe","pull .... add .....commit...push","add...commit...push", "commit...push", "push",1,0))
        myquestions.add(Question(0,"1003 X 964 = ","986892","966892", "976892", "996892",2,0))
        myquestions.add(Question(0,"15 - (3*3) / 6 = ","0","1", "2", "3",2,0))
        var quz= if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Quiz(0,currentTeacher,5,10,"abcd1234", "iths20", LocalDate.now().toString()/*,questions = myquestions*/)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        allQuizes.add(quz)
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()

        db = AppDatabase.getInstance(this)





        var btnGetTheQuiz=findViewById<Button>(R.id.btnGetTheQuiz)
        var QuizCode=findViewById<EditText>(R.id.txtQuizCode)
        var QuizCodeNotFound=findViewById<TextView>(R.id.txtError)
        var btnTeacher=findViewById<Button>(R.id.btnTeacher)
        var btnGeneralInfo=findViewById<ImageButton>(R.id.informationImageButton)
        btnGetTheQuiz.setOnClickListener(){
            QuizCodeNotFound.visibility=View.INVISIBLE

            val quizeswithcode=async(Dispatchers.IO) {
                db.quizDao.getQuizByQuizCode(QuizCode.text.trim().toString())
            }
            launch{

                if (quizeswithcode.await().size>0){
                    goToCurrentStudentActivity(QuizCode.text.toString())
                }
                else{
                    QuizCodeNotFound.text="Quiz Not Found"
                    QuizCodeNotFound.visibility=View.VISIBLE
                }
            }

        }
        btnTeacher.setOnClickListener(){
            goToTeacherLoginActivity()
        }
        btnGeneralInfo.setOnClickListener(){
            val teachers=async(Dispatchers.IO) {
                db.teacherDao.getAllTeachers()
            }
            launch {
                val list=teachers.await().toMutableList()
                allTeachers= list
                //Log.d("Zak", allTeachers[0].teacherName!!)
                goToGeneralInfoActivity()
            }

        }
    }



    fun goToGeneralInfoActivity(){
        var intent=Intent(this,GeneralInformation::class.java)
        startActivity(intent)
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