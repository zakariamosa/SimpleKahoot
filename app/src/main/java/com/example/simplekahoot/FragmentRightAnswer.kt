package com.example.simplekahoot

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.Thread.sleep

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRightAnswer.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRightAnswer : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fitchnextquestion()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_right_answer, container, false)


    }

    fun fitchnextquestion(){
        val p1=this.param1
        val p2=this.param2!!
        val p3=this.param3!!
        val frg=this.fragmentManager
        playsound()
        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (p2=="lastquestion"){
                    val fragment = FragmentStudentResult.newInstance(p1!!,p2,p3)
                    val transaction = frg?.beginTransaction()
                    transaction?.replace(R.id.container, fragment, "FragmentStudentResult")
                    transaction?.commit()
                }else{
                    val fragment = StudentQuestion.newInstance(p1!!,p2)
                    val transaction = frg?.beginTransaction()
                    transaction?.replace(R.id.container, fragment, "studentQuestionFragment")
                    transaction?.commit()
                }
               stopSound()
            }
        }
        timer.start()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRightAnswer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,param3: String) =
            FragmentRightAnswer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }


    fun playsound(){
        mediaPlayer = MediaPlayer.create(context,R.raw.correctanswer)
        mediaPlayer.start()
    }
    fun stopSound(){
        mediaPlayer.stop()
    }
}