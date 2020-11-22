package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeacherLogin : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_login)

        job = Job()

        db = AppDatabase.getInstance(this)

        var btnLoginTeacher=findViewById<Button>(R.id.btnLogin)
        var txtemail=findViewById<EditText>(R.id.txtNewTeacherEmail)
        var txtpwd=findViewById<EditText>(R.id.txtNewTeacherPassword)
        var txterrorlogin=findViewById<TextView>(R.id.txterrorLogin)
        var btnNewTeacher=findViewById<Button>(R.id.btnNewTeacher)
        btnLoginTeacher.setOnClickListener(){
            txterrorlogin.visibility= View.INVISIBLE
            val teacher=async(Dispatchers.IO) {
                db.teacherDao.getTeacherByEmailAndPassword(txtemail.text.toString(),txtpwd.text.toString())
            }
            launch{
                currentTeacher=teacher.await()[0]
                goToTeacherHomeActivity()
            }

        }
        btnNewTeacher.setOnClickListener(){
            goToNewTeacherActivity()
        }
    }
/*
    fun validLogin(email:String,password:String):Boolean{
        for (teacher in allTeachers){
            if (teacher.email==email&&teacher.password==password){
                currentTeacher=teacher
                return true
            }
        }
        return false
    }
*/
    private fun validLogin(email:String,password:String):Boolean {
        launch(Dispatchers.IO){
            var teacher=db.teacherDao.getTeacherByEmailAndPassword(email,password)
            if (teacher!=null){
                currentTeacher=teacher[0]
            }
        }
        return when(currentTeacher){
            null->false
            else->true
        }
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