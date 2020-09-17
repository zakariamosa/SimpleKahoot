package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.NestedScrollView

class TeacherHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)

        var btnNewQuiz=findViewById<Button>(R.id.btnNewQuiz)
        btnNewQuiz.setOnClickListener(){
            var intent= Intent(this,TeacherQuiz::class.java)
            startActivity(intent)
        }


        var txtallquizes: EditText =findViewById(R.id.txtTeacherQuizes)
        for (quz in allQuizes){
            if (quz.QuizTeacher== currentTeacher){
                txtallquizes.setText("${txtallquizes.text.toString()} \n Quiz Name: ${quz.quizName} ......Quiz Date: ${quz.quizDate.toString()}..... Quiz code: ${quz.quizCode}")
            }
        }


        var btnlogout:Button=findViewById(R.id.btnLogout)
        btnlogout.setOnClickListener(){
            var intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}