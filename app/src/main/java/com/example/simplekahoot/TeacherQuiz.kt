package com.example.simplekahoot

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.coroutines.CoroutineContext

class TeacherQuiz : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase
    @RequiresApi(Build.VERSION_CODES.O)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_quiz)

        job = Job()

        db = AppDatabase.getInstance(this)

        var btncreatequiz=findViewById<Button>(R.id.btnCreateNewQuiz)
        var numberofquestions=findViewById<EditText>(R.id.txtNumberofQuestions)
        var timeforeachquestion=findViewById<EditText>(R.id.txtTimeForEachQuestion)
        var quizname=findViewById<EditText>(R.id.txtQuizName)

        btncreatequiz.setOnClickListener(){
            try {

                var quz=Quiz(0,currentTeacher,numberofquestions.text.toString().toInt(),timeforeachquestion.text.toString().toInt(),"",quizname.text.toString())

            quz.generateQuizCode()





                launch(Dispatchers.IO){
                    db.quizDao.insertQuiz(quz)
                    allQuizes.add(quz)
                    var intent= Intent(this@TeacherQuiz,MainQuestion::class.java)
                    intent.putExtra("myQuizCode",quz.quizCode)
                    intent.putExtra("questionNumber",numberofquestions.text.toString())
                    startActivity(intent)
                }


            }catch (exc:Throwable){
                Log.d("!!!",exc.message.toString())
            }




        }




    }


}