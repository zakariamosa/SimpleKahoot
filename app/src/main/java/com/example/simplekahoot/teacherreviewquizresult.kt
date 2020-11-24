package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class teacherreviewquizresult : AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacherreviewquizresult)


        job = Job()
        db = AppDatabase.getInstance(this)
        val quzcode = intent.getStringExtra("quizCode").toString()

        val StudentsResult=async(Dispatchers.IO) {
            db.studentDao.getStudentsByQuizCode(quzcode)
        }
        launch{
            val myAdapter=TeacherQuizGeneralResultRecyclerAdapter(this@teacherreviewquizresult,StudentsResult.await())
            val myRecyclerView=findViewById<RecyclerView>(R.id.recyclerviewstudentsresultforthespecificquiz)
            myRecyclerView.layoutManager= LinearLayoutManager(this@teacherreviewquizresult)
            myRecyclerView.adapter=myAdapter

        }

    }
}