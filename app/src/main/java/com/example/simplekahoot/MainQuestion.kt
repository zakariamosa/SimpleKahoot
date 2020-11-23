package com.example.simplekahoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlinx.coroutines.*
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class MainQuestion : AppCompatActivity(), CoroutineScope {


    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase



        lateinit var question:EditText
        lateinit var answer1:EditText
        lateinit var answer2:EditText
        lateinit var answer3:EditText
        lateinit var answer4:EditText
        lateinit var rad1:RadioButton
        lateinit var rad2:RadioButton
        lateinit var rad3:RadioButton
        lateinit var rad4:RadioButton
        lateinit var quzcode: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_question)

try {



        job = Job()

        db = AppDatabase.getInstance(this)


         question = findViewById<EditText>(R.id.txtQuestion)
         answer1 = findViewById<EditText>(R.id.txtAnswer1)
         answer2 = findViewById<EditText>(R.id.txtAnswer2)
         answer3 = findViewById<EditText>(R.id.txtAnswer3)
         answer4 = findViewById<EditText>(R.id.txtAnswer4)
         rad1 = findViewById<RadioButton>(R.id.rbAnswer1)
         rad2 = findViewById<RadioButton>(R.id.rbAnswer2)
         rad3 = findViewById<RadioButton>(R.id.rbAnswer3)
         rad4 = findViewById<RadioButton>(R.id.rbAnswer4)






        var questionNumber = intent.getStringExtra("questionNumber")
        var designQuestionNumber = findViewById<TextView>(R.id.txtQuestionNumber)
        var realquestionnumber:Int=1
        designQuestionNumber.text = "QuestionNumber: "+realquestionnumber.toString()
        var btnSaveQuestion = findViewById<Button>(R.id.btnSaveQuestion)
        quzcode= intent.getStringExtra("myQuizCode").toString()

        btnSaveQuestion.setOnClickListener() {
            var RightAnswer: Int = 0
            if (rad1.isChecked) {
                RightAnswer = 1
            }
            if (rad2.isChecked) {

                RightAnswer = 2

            }
            if (rad3.isChecked) {
                RightAnswer = 3
            }
            if (rad4.isChecked) {
                RightAnswer = 4
            }

            var quizId:Int=0

            val thisquestionquizinfo=async(Dispatchers.IO) {
                db.quizDao.getQuizByQuizCode(quzcode)
            }
            launch {
                quizId = thisquestionquizinfo.await()[0].quizId


                var q = Question(
                    0,
                    question.text.toString(),
                    answer1.text.toString(),
                    answer2.text.toString(),
                    answer3.text.toString(),
                    answer4.text.toString(),
                    RightAnswer,
                    quizId
                )
                launch(Dispatchers.IO) {
                    db.questionDao.insertQuestion(q)
                }
                    question.setText("")
                    answer1.setText("")
                    answer2.setText("")
                    answer3.setText("")
                    answer4.setText("")
                    rad1.isChecked=true
                    rad2.isChecked=false
                    rad3.isChecked=false
                    rad4.isChecked=false
                    if (realquestionnumber<questionNumber!!.toInt()) {
                        realquestionnumber += 1
                        designQuestionNumber.text="QuestionNumber: " + realquestionnumber.toString()
                        //Toast.makeText(this@MainQuestion, designQuestionNumber.text, Toast.LENGTH_SHORT).show()
                    }
                    else{
                        var intent= Intent(this@MainQuestion,TeacherHome::class.java)
                        startActivity(intent)
                    }

            }

        }



}catch (exc:Throwable){
    Log.d("ZZZZZ",exc.message.toString())
}



    }



}