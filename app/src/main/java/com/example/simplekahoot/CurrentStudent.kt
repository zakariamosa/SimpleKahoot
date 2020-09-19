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

        val quzcode= intent.getStringExtra("QuizCode").toString()

        var btncontinue=findViewById<Button>(R.id.btnStudentContinueToQuiz)
        val txtstudentname=findViewById<EditText>(R.id.txtCurrentStudent)
        val txterror=findViewById<TextView>(R.id.txterrorstudentname)
        btncontinue.setOnClickListener(){
            if (txtstudentname.text.trim()==""){
                txterror.text="Invalid Student Name"
            }
            else
            {
                currentStudent= Student(txtstudentname.text.toString(),0.0)
                allStudents.add(currentStudent)
                //val intent=Intent(this, StudentQuizQuestion::class.java)

                val intent=Intent(this, StudentQuiz::class.java)

                intent.putExtra("QuizCode",quzcode)

                startActivity(intent)
            }
        }
    }
}