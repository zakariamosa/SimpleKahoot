package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CurrentStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_student)


        var btncontinue=findViewById<Button>(R.id.btnStudentContinueToQuiz)
        val txtstudentname=findViewById<EditText>(R.id.txtCurrentStudent)
        val txterror=findViewById<TextView>(R.id.txterrorstudentname)
        btncontinue.setOnClickListener(){
            if (txtstudentname.text.trim()==""){
                txterror.text="Invalid Student Name"
            }
            else
            {
                val intent=Intent(this, StudentQuizQuestion::class.java)
                startActivity(intent)
            }
        }
    }
}