package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TeacherHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)

        var btnNewQuiz=findViewById<Button>(R.id.btnNewQuiz)
        btnNewQuiz.setOnClickListener(){
            var intent= Intent(this,TeacherQuiz::class.java)
            startActivity(intent)
        }
    }
}