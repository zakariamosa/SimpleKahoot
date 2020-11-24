package com.example.simplekahoot

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class QuizRecyclerAdapter(val context: Context, val quizes: List<Quiz>):RecyclerView.Adapter<QuizRecyclerAdapter.ViewHolder>(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    val layoutInflator= LayoutInflater.from(context)
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun getItemCount(): Int {
        return quizes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=layoutInflator.inflate(R.layout.list_quizes_items, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teacherQuizItewm=quizes[position]
        holder.quizName.text=teacherQuizItewm.quizName
        holder.quizDate.text=teacherQuizItewm.quizDate.toString()
        holder.quizCode.text=teacherQuizItewm.quizCode
        holder.btnCopyQuizCode.setOnClickListener(){
            myClip = ClipData.newPlainText("text", teacherQuizItewm.quizCode)
            myClipboard?.setPrimaryClip(myClip!!)

            var textToCopy = ""
            textToCopy=teacherQuizItewm.quizCode
            val clipboardManager =
                this@QuizRecyclerAdapter.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(
                this@QuizRecyclerAdapter.context,
                "Text copied to clipboard",
                Toast.LENGTH_LONG
            ).show()
        }
        holder.btnViewStudentsResults.setOnClickListener(){
            job = Job()
            db = AppDatabase.getInstance(this@QuizRecyclerAdapter.context)

            val StudentsResult=async(Dispatchers.IO) {
                db.studentDao.getStudentsByQuizCode(teacherQuizItewm.quizCode)
            }
            launch{
                val intent=Intent(this@QuizRecyclerAdapter.context, teacherreviewquizresult::class.java)
                intent.putExtra("quizCode",teacherQuizItewm.quizCode)
                this@QuizRecyclerAdapter.context.startActivity(intent)
            }
            /*Toast.makeText(
                this@QuizRecyclerAdapter.context,
                "Quiz Id: ${teacherQuizItewm.quizId} And Teacher Id: ${teacherQuizItewm.QuizTeacher.id.toString()}",
                Toast.LENGTH_LONG
            ).show()*/
        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val quizName=itemView.findViewById<TextView>(R.id.txtViewMyQuizName)
        val quizDate=itemView.findViewById<TextView>(R.id.txtViewMyQuizDate)
        val quizCode=itemView.findViewById<TextView>(R.id.txtViewMyQuizCode)
        val btnCopyQuizCode=itemView.findViewById<Button>(R.id.btnCopyQuizCode)
        val btnViewStudentsResults=itemView.findViewById<Button>(R.id.btnListAllMyStudentsResultat)

    }
}