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
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class TeacherQuiz : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_quiz)

        var btncreatequiz=findViewById<Button>(R.id.btnCreateNewQuiz)
        var numberofquestions=findViewById<EditText>(R.id.txtNumberofQuestions)
        var timeforeachquestion=findViewById<EditText>(R.id.txtTimeForEachQuestion)
        var quizname=findViewById<EditText>(R.id.txtQuizName)

        btncreatequiz.setOnClickListener(){
            try {

            var quz=Quiz(currentTeacher,numberofquestions.text.toString().toInt(),timeforeachquestion.text.toString().toInt(),"",quizname.text.toString())

            quz.generateQuizCode()
            allQuizes.add(quz)



                var intent= Intent(this,MainQuestion::class.java)
                intent.putExtra("myQuizCode",quz.quizCode)
                intent.putExtra("questionNumber",numberofquestions.text.toString())
                startActivity(intent)

            }catch (exc:Throwable){
                Log.d("!!!",exc.message.toString())
            }




        }




    }
}