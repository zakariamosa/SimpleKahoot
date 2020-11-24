package com.example.simplekahoot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_student_result.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentStudentResult.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentStudentResult : Fragment(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

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
        // Inflate the layout for this fragment


        job = Job()
        db = AppDatabase.getInstance(this@FragmentStudentResult.context!!)

        val view=inflater.inflate(R.layout.fragment_student_result, container, false)
        val studentresult=view.findViewById<TextView>(R.id.txtViewStudentResult)
        studentresult.setText(param1)

        val btnshwcorrectanswrs=view.findViewById<Button>(R.id.btnShowCorrectAnswers)
        btnshwcorrectanswrs.setOnClickListener(){
            gotocorrectanswers()
        }

        launch(Dispatchers.IO){
            db.studentDao.updateStudentScore(param1!!.substringBefore("/").toDouble(), currentStudent.studentId,param3!!)
        }

        return view
    }

    fun gotocorrectanswers(){
        val fragment = fragmentcorrectanswersforquiz.newInstance(param1!!, param2!!,param3!!)
        val transaction = this.fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment, "rightAnswerFragment")
        transaction?.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentStudentResult.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            FragmentStudentResult().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}