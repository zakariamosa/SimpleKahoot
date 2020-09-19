package com.example.simplekahoot

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StudentQuestion : Fragment() {


    lateinit var question: TextView
    lateinit var answer1: TextView
    lateinit var answer2: TextView
    lateinit var answer3: TextView
    lateinit var answer4: TextView
    lateinit var qustnmbr:TextView
    lateinit var myprogressbar:ProgressBar

    lateinit var txtscore:TextView
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_question, container, false)


        question = view.findViewById<TextView>(R.id.txtViewStudentQuestionFragment)
        answer1 = view.findViewById<TextView>(R.id.txtStudentAnswerNo1)
        answer2 = view.findViewById<TextView>(R.id.txtStudentAnswerNo2)
        answer3 = view.findViewById<TextView>(R.id.txtStudentAnswerNo3)
        answer4 = view.findViewById<TextView>(R.id.txtStudentAnswerNo4)
        qustnmbr = view.findViewById<TextView>(R.id.txtQuestionNumberForStudentFragment)
        myprogressbar = view.findViewById<ProgressBar>(R.id.progBarTimerFragment)



        txtscore = view.findViewById<TextView>(R.id.txtScoreFragment)


        LoadQuestion(1)


        return view
    }


    fun LoadQuestion(QuestionNumber:Int){






        var totalquestions: Int


        var quizquestions = allQuizes.filter { Quiz -> Quiz.quizCode == param1 }
        totalquestions = quizquestions[0]?.numberOfQuestions
        qustnmbr.setText("${qustnmbr.hint}: $QuestionNumber / ${totalquestions.toString()}")
        question.setText(quizquestions[0]?.questions?.get(QuestionNumber-1)?.question)
        answer1.setText(quizquestions[0]?.questions?.get(QuestionNumber-1)?.alternativeAnswer1)
        answer2.setText(quizquestions[0]?.questions?.get(QuestionNumber-1)?.alternativeAnswer2)
        answer3.setText(quizquestions[0]?.questions?.get(QuestionNumber-1)?.alternativeAnswer3)
        answer4.setText(quizquestions[0]?.questions?.get(QuestionNumber-1)?.alternativeAnswer4)



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
                calculateScore(0,false)
            }
        }
        timer.start()


        answer1.setOnTouchListener() { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.GRAY)
                    myprogressbar.incrementProgressBy(0)
                    timer.cancel()
                    val isRightAnswer= quizquestions[0]?.questions?.get(QuestionNumber-1)?.rightAnswer==1
                    calculateScore(myprogressbar.progress,isRightAnswer)
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
                    val isRightAnswer= quizquestions[0]?.questions?.get(QuestionNumber-1)?.rightAnswer==2
                    calculateScore(myprogressbar.progress,isRightAnswer)
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
                    val isRightAnswer= quizquestions[0]?.questions?.get(QuestionNumber-1)?.rightAnswer==3
                    calculateScore(myprogressbar.progress,isRightAnswer)
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
                    val isRightAnswer= quizquestions[0]?.questions?.get(QuestionNumber-1)?.rightAnswer==4
                    calculateScore(myprogressbar.progress,isRightAnswer)
                }

            }
            true

        }
    }


    fun calculateScore(currentprog: Int,isRightAnswer:Boolean) {
        if (isRightAnswer){
            currentStudent.Score+=90.0
            currentStudent.Score+=(currentprog/20.0)

            val fragment = FragmentRightAnswer()
            val transaction = this.fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment, "rightAnswerFragment")
            transaction?.commit()
        }
        else{
            val fragment = FragmentWrongAnswer()
            val transaction = this.fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, fragment, "wrongAnswerFragment")
            transaction?.commit()
        }



        txtscore.text = currentStudent.Score.toString()//currentprog.toString()
    }

    fun resetAlt(){
        question.setText("")
        answer1.setText("")
        answer2.setText("")
        answer3.setText("")
        answer4.setText("")
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
        fun newInstance(param1: String, param2: String) =
            StudentQuestion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }







}
