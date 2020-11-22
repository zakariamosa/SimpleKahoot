package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeacherHome : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)

        job = Job()
        db = AppDatabase.getInstance(this)

        var btnNewQuiz=findViewById<Button>(R.id.btnNewQuiz)
        btnNewQuiz.setOnClickListener(){
            var intent= Intent(this,TeacherQuiz::class.java)
            startActivity(intent)
        }


        var myRecyclerView=findViewById<RecyclerView>(R.id.recyclerViewQuizes)
        myRecyclerView.layoutManager= LinearLayoutManager(this)
        val teacherQuizes=async(Dispatchers.IO) {
            db.quizDao.getTeacherQuizesByTeacherId(currentTeacher.id)
        }
        launch{

            val myAdapter=QuizRecyclerAdapter(this@TeacherHome,teacherQuizes.await())
            myRecyclerView.adapter=myAdapter
        }


        /*var txtallquizes: EditText =findViewById(R.id.txtTeacherQuizes)
        for (quz in allQuizes){
            if (quz.QuizTeacher== currentTeacher){
                txtallquizes.setText("${txtallquizes.text.toString()} \n Quiz Name: ${quz.quizName} ......Quiz Date: ${quz.quizDate.toString()}..... Quiz code: ${quz.quizCode}")
            }
        }*/


        var btnlogout:Button=findViewById(R.id.btnLogout)
        btnlogout.setOnClickListener(){
            var intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

/*
        var TeacherQuizes= allQuizes.filter { q->q.QuizTeacher== currentTeacher }
        var myRecyclerView=findViewById<RecyclerView>(R.id.recyclerViewQuizes)
        myRecyclerView.layoutManager= LinearLayoutManager(this)
        val myAdapter=QuizRecyclerAdapter(this,TeacherQuizes)

        myRecyclerView.adapter=myAdapter
*/

    }
}