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
import kotlinx.coroutines.Dispatchers.IO
import kotlin.coroutines.CoroutineContext

class NewTeacher : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_teacher)

        job = Job()

        db = AppDatabase.getInstance(this)
        /*Room.databaseBuilder(applicationContext, AppDatabase::class.java, "teachers")
            .fallbackToDestructiveMigration()
            .build()*/

        loadAllteachers()

        var btnCreateNewTeacher=findViewById<Button>(R.id.btnCreateNewTeacher)
        var txterrorNewTeacher=findViewById<TextView>(R.id.txtError2)
        var txtemail=findViewById<EditText>(R.id.txtNewTeacherEmail)
        var txtNewTeacherPassword=findViewById<EditText>(R.id.txtNewTeacherPassword)
        var txtNewTeacherName=findViewById<EditText>(R.id.txtTeacherName)
        btnCreateNewTeacher.setOnClickListener(){
            txterrorNewTeacher.visibility= View.INVISIBLE
            if(ValidNewTeacher(txtemail.text.toString(),txtNewTeacherName.text.toString(),txtNewTeacherPassword.text.toString())){
                currentTeacher=Teacher(0,txtNewTeacherName.text.toString(),txtemail.text.toString(),txtNewTeacherPassword.text.toString())

                launch(IO){
                    db.teacherDao.insertTeacher(currentTeacher)

                val teacher=async(Dispatchers.IO) {
                    db.teacherDao.getTeacherByEmailAndPassword(currentTeacher.email!!,currentTeacher.password!!)
                }
                launch{
                    currentTeacher=teacher.await()[0]
                    allTeachers.add(currentTeacher)
                    goToTeacherHomeActivity()
                }
                }
            }  else{
                   txterrorNewTeacher.visibility=View.VISIBLE
                txterrorNewTeacher.text="Invalid New Teacher"
            }

        }
    }

    fun loadAllteachers(){
        val teachers=async(Dispatchers.IO) {
            db.teacherDao.getAllTeachers()
        }
        launch {
            val list=teachers.await().toMutableList()
            allTeachers= list

        }
    }



    fun ValidNewTeacher(email:String,name:String,pwd:String):Boolean{
             return (email.trim()!=""&&name.trim()!=""&&pwd.trim()!=""&& !emailexistindb(email))
    }

    private fun emailexistindb(email:String): Boolean {
        for (teacher in allTeachers){
            if (teacher.email!!.trim()==email.trim()){
                return true
            }
        }
        return false
    }

    fun goToTeacherHomeActivity(){
            var intent= Intent(this,TeacherHome::class.java)
            startActivity(intent)
}


}