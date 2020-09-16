package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TeacherLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_login)

        var btnLoginTeacher=findViewById<Button>(R.id.btnLogin)
        var txtemail=findViewById<EditText>(R.id.txtNewTeacherEmail)
        var txtpwd=findViewById<EditText>(R.id.txtNewTeacherPassword)
        var txterrorlogin=findViewById<TextView>(R.id.txterrorLogin)
        var btnNewTeacher=findViewById<Button>(R.id.btnNewTeacher)
        btnLoginTeacher.setOnClickListener(){
            txterrorlogin.visibility= View.INVISIBLE
            var validlogin=validLogin(txtemail.text.toString(),txtpwd.text.toString())
            if(validlogin){
                goToTeacherHomeActivity()
            }else{
                txterrorlogin.text="Invalid Login"
                txterrorlogin.visibility= View.VISIBLE
            }
        }
        btnNewTeacher.setOnClickListener(){
            goToNewTeacherActivity()
        }
    }

    fun validLogin(email:String,password:String):Boolean{
        for (teacher in allTeachers){
            if (teacher.email==email&&teacher.password==password){
                currentTeacher=teacher
                return true
            }
        }
        return false
    }

    fun goToTeacherHomeActivity(){
        var intent= Intent(this,TeacherHome::class.java)
        startActivity(intent)
    }

    fun goToNewTeacherActivity(){
        var intent=Intent(this,NewTeacher::class.java)

        startActivity(intent)
    }
}