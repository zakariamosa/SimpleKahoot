package com.example.simplekahoot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class teacherreviewspecificstudentresult : AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacherreviewspecificstudentresult)

        job = Job()
        db = AppDatabase.getInstance(this)

        //val abc = intent.getStringExtra("abc").toString()

        val quzcode = specificStudentQuizCode//intent.getStringExtra("quizCode").toString()
        val studentId = specificStudentId//intent.getStringExtra("studentId").toString()



        val Studentsquiz=async(Dispatchers.IO) {
            db.quizDao.getQuizByQuizCode(quzcode!!)
        }
        launch {
            val quzId=Studentsquiz.await()[0].quizId
            val Studentsquizlistofquestions=async(Dispatchers.IO) {
                db.questionDao.getAllQuestionsTillSpecificQuiz(quzId)
            }
            launch {
                val StudentsResulttransactionDetails=async(Dispatchers.IO) {
                    db.transactionDetailsDao.gettransactiondetailstoAspecificUserAndQuiz(
                        quzcode!!,
                        studentId!!.toInt()
                    )
                }
                launch{
                    val rcyklview = findViewById<RecyclerView>(R.id.recyclerViewteacherreviewspecificstudentresult)
                    rcyklview.layoutManager = LinearLayoutManager(this@teacherreviewspecificstudentresult)
                    val myadapter = AdapterQuizQuestionsAnswers(
                        this@teacherreviewspecificstudentresult,
                        Studentsquizlistofquestions.await()!! as MutableList<Question>,
                        quzcode!!,
                        StudentsResulttransactionDetails.await()
                    )
                    rcyklview.adapter = myadapter
                }
            }

        }



    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val quzcode = intent?.getStringExtra("quizCode").toString()
        val studentId = intent?.getStringExtra("studentId")
    }
}