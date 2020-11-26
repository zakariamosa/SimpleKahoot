package com.example.simplekahoot

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeacherQuizGeneralResultRecyclerAdapter (val context: Context, val students: List<Student>, val quizcode:String):RecyclerView.Adapter<TeacherQuizGeneralResultRecyclerAdapter.ViewHolder>(),
    CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    val layoutInflator= LayoutInflater.from(context)
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=layoutInflator.inflate(R.layout.list_students_results_for_quiz, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentResultItewm=students[position]
        holder.studentName.text=studentResultItewm.StudentName
        holder.studentResult.text=studentResultItewm.Score.toString()

        val studentId:String=studentResultItewm.studentId.toString()
        val quizcodee:String=quizcode

        holder.btnimageButtonShowDetails.setOnClickListener(){

            val intent= Intent(this@TeacherQuizGeneralResultRecyclerAdapter.context, teacherreviewspecificstudentresult::class.java)
            //intent.putExtra("studentId",studentId)
            specificStudentId=studentId
            //intent.putExtra("quizcode",quizcodee)
            specificStudentQuizCode=quizcodee
            this@TeacherQuizGeneralResultRecyclerAdapter.context.startActivity(intent)
            /*intent.putExtra("studentId",studentResultItewm.studentId.toString())
            intent.putExtra("quizcode",quizcode)*/
            //this@TeacherQuizGeneralResultRecyclerAdapter.context.startActivity(intent)
            /*val fragment = fragmentcorrectanswersforquiz.newInstance("", "",param3!!)
            val transaction = this.fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment, "rightAnswerFragment")
            transaction?.commit()*/
        }

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val studentName=itemView.findViewById<TextView>(R.id.txtViewStudentnameForTeacherView)
        val studentResult=itemView.findViewById<TextView>(R.id.txtViewStudentQuizResut)

        val btnimageButtonShowDetails=itemView.findViewById<ImageButton>(R.id.imageButtonShowDetails)

    }


}