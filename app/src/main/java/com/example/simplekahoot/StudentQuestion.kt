package com.example.simplekahoot


import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 ="transactionId"
private var Quiz_Code="0"

class StudentQuestion : Fragment(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase


    lateinit var question: TextView
    lateinit var answer1: TextView
    lateinit var answer2: TextView
    lateinit var answer3: TextView
    lateinit var answer4: TextView
    lateinit var qustnmbr:TextView
    lateinit var myprogressbar:ProgressBar
    private lateinit var mediaPlayer: MediaPlayer
    lateinit var currentQuestion: Question
    private var CurrentStudentAnswer:Int=0

    lateinit var txtscore:TextView
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var transactionId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            transactionId = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        param3=param1

        val view = inflater.inflate(R.layout.fragment_student_question, container, false)

        job = Job()

        db = AppDatabase.getInstance(this@StudentQuestion.context!!)


        question = view.findViewById<TextView>(R.id.txtViewStudentQuestionFragment)
        answer1 = view.findViewById<TextView>(R.id.txtStudentAnswerNo1)
        answer2 = view.findViewById<TextView>(R.id.txtStudentAnswerNo2)
        answer3 = view.findViewById<TextView>(R.id.txtStudentAnswerNo3)
        answer4 = view.findViewById<TextView>(R.id.txtStudentAnswerNo4)
        qustnmbr = view.findViewById<TextView>(R.id.txtQuestionNumberForStudentFragment)
        myprogressbar = view.findViewById<ProgressBar>(R.id.progBarTimerFragment)

        CurrentStudentAnswer=0

        txtscore = view.findViewById<TextView>(R.id.txtScoreFragment)
        playsound()
        LoadQuestion(param2!!.toInt())








        return view
    }


    fun LoadQuestion(QuestionNumber: Int){






        var totalquestions: Int


        val quizquestions=async(Dispatchers.IO) {
            db.quizDao.getQuizWithQuestions(currentStudent.quizCode)//zakkkkkkkk
        }
        launch {
            totalquestions = quizquestions.await()[0]?.quiz.numberOfQuestions
            qustnmbr.setText("${qustnmbr.hint}: $QuestionNumber / ${totalquestions.toString()}")
            question.setText(quizquestions.await()[0]?.questions?.get(QuestionNumber - 1)?.question)
            answer1.setText(quizquestions.await()[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer1)
            answer2.setText(quizquestions.await()[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer2)
            answer3.setText(quizquestions.await()[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer3)
            answer4.setText(quizquestions.await()[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer4)


            //var quizquestions = allQuizes.filter { Quiz -> Quiz.quizCode == param1 }
            //totalquestions = quizquestions[0]?.numberOfQuestions
            //qustnmbr.setText("${qustnmbr.hint}: $QuestionNumber / ${totalquestions.toString()}")
            //question.setText(quizquestions[0]?.questions?.get(QuestionNumber - 1)?.question)
            //answer1.setText(quizquestions[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer1)
            //answer2.setText(quizquestions[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer2)
            //answer3.setText(quizquestions[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer3)
            //answer4.setText(quizquestions[0]?.questions?.get(QuestionNumber - 1)?.alternativeAnswer4)

            currentQuestion = quizquestions.await()[0]?.questions?.get(QuestionNumber - 1)!!

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                myprogressbar.min = 0
            }
            myprogressbar.max = 200
            myprogressbar.incrementProgressBy(200)
            val timer = object : CountDownTimer(20000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    myprogressbar.incrementProgressBy(-10)

                }

                override fun onFinish() {
                    myprogressbar.setBackgroundColor(Color.RED)
                    calculateScore(0, false, QuestionNumber, totalquestions, false)
                    stopSound()
                }
            }
            timer.start()


            CurrentStudentAnswer = 0

            answer1.setOnTouchListener() { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        v.setBackgroundColor(Color.GRAY)
                        myprogressbar.incrementProgressBy(0)
                        timer.cancel()
                        val isRightAnswer =quizquestions.getCompleted()[0]?.questions?.get(QuestionNumber - 1)?.rightAnswer == 1
                        CurrentStudentAnswer = 1
                        calculateScore(
                            myprogressbar.progress,
                            isRightAnswer,
                            QuestionNumber,
                            totalquestions,
                            true
                        )
                    }

                }
                true

            }

            answer2.setOnTouchListener() { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        v.setBackgroundColor(Color.GRAY)
                        myprogressbar.incrementProgressBy(0)
                        timer.cancel()
                        val isRightAnswer =quizquestions.getCompleted()[0]?.questions?.get(QuestionNumber - 1)?.rightAnswer == 2
                        CurrentStudentAnswer = 2
                        calculateScore(
                            myprogressbar.progress,
                            isRightAnswer,
                            QuestionNumber,
                            totalquestions,
                            true
                        )
                    }

                }
                true

            }

            answer3.setOnTouchListener() { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        v.setBackgroundColor(Color.GRAY)
                        myprogressbar.incrementProgressBy(0)
                        timer.cancel()
                        val isRightAnswer =quizquestions.getCompleted()[0]?.questions?.get(QuestionNumber - 1)?.rightAnswer == 3
                        CurrentStudentAnswer = 3
                        calculateScore(
                            myprogressbar.progress,
                            isRightAnswer,
                            QuestionNumber,
                            totalquestions,
                            true
                        )
                    }

                }
                true

            }

            answer4.setOnTouchListener() { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        v.setBackgroundColor(Color.GRAY)
                        myprogressbar.incrementProgressBy(0)
                        timer.cancel()
                        val isRightAnswer =quizquestions.getCompleted()[0]?.questions?.get(QuestionNumber - 1)?.rightAnswer == 4
                        CurrentStudentAnswer = 4
                        calculateScore(
                            myprogressbar.progress,
                            isRightAnswer,
                            QuestionNumber,
                            totalquestions,
                            true
                        )
                    }

                }
                true

            }

        }

    }


    fun calculateScore(
        currentprog: Int,
        isRightAnswer: Boolean,
        thisquestionnumber: Int,
        totalquestionsnumber: Int,
        inserttransactiondetails: Boolean
    ) {





        var nextquestionnumber:String
        if (thisquestionnumber==totalquestionsnumber&&isRightAnswer){
            nextquestionnumber="lastquestion"
            param1="${(currentStudent.Score+90.0+(currentprog/20.0)).toString()} / ${(totalquestionsnumber*100).toString()}"
        }
        else if(thisquestionnumber==totalquestionsnumber&&!isRightAnswer){
            nextquestionnumber="lastquestion"
            param1="${(currentStudent.Score).toString()} / ${(totalquestionsnumber*100).toString()}"
        }else{
            nextquestionnumber=(thisquestionnumber!!.toInt()+1).toString()
            Quiz_Code=param1!!
        }

        if (isRightAnswer){
            currentStudent.Score+=90.0
            currentStudent.Score+=(currentprog/20.0)


            val fragment = FragmentRightAnswer.newInstance(param1!!, nextquestionnumber.toString(),param3!!)
            val transaction = this.fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment, "rightAnswerFragment")
            transaction?.commit()
        }
        else{
            val fragment = FragmentWrongAnswer.newInstance(param1!!, nextquestionnumber.toString(),param3!!)
            val transaction = this.fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment, "wrongAnswerFragment")
            transaction?.commit()
        }



        txtscore.text = currentStudent.Score.toString()//currentprog.toString()

        if (inserttransactiondetails){
            insertTransactionDetail(txtscore.text.toString().toDouble())
        }
        stopSound()
    }

    fun insertTransactionDetail(studentcurrentscour:Double){

        launch(Dispatchers.IO) {
            db.transactionDetailsDao.insertTransactionDetails(TransactionDetails(0,currentQuestion,CurrentStudentAnswer,
                currentStudent,studentcurrentscour,transactionId!!.toInt()))
            val transaction=async(Dispatchers.IO) {
                db.transactionDao.getTransactionAllDetailsByQuizCodeForAspecificStudent(
                    currentStudent.quizCode,
                    currentStudent.studentId)
            }
            launch {
                allTransactionDetails.add(TransactionDetails(transaction.await()[0].transactionDetails[0].transactionDetailsId,currentQuestion,CurrentStudentAnswer,
                    currentStudent,studentcurrentscour,transactionId!!.toInt()))
            }
        }

    }

    fun playsound(){
        mediaPlayer = MediaPlayer.create(context,R.raw.clockticks)
        mediaPlayer.start()
    }
    fun stopSound(){
        mediaPlayer.stop()
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment YellowFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, transactionId: String) =
            StudentQuestion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, transactionId)
                }
            }
    }







}

