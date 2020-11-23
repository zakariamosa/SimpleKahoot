package com.example.simplekahoot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentcorrectanswersforquiz.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentcorrectanswersforquiz : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db:AppDatabase

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
        val view= inflater.inflate(R.layout.fragment_fragmentcorrectanswersforquiz, container, false)

        job = Job()

        db = AppDatabase.getInstance(this@fragmentcorrectanswersforquiz.context!!)



        //var studenttransactiondetailsforthisquiz=allTransactionDetails.filter { td->td.student.quizCode==param3 &&td.student.StudentName== currentStudent.StudentName}
        val gettransactiondetailstothecurrenttransaction=async(Dispatchers.IO) {
            db.transactionDetailsDao.gettransactiondetailstothecurrenttransaction(currentQuizCode,currenttransactionId.toInt())
        }
        launch {
            val questions=async(Dispatchers.IO) {
                db.questionDao.getAllQuestionsTillSpecificQuiz(gettransactiondetailstothecurrenttransaction.await()[0].question.quizId)//zakkkkkkkk
            }
            launch {

                //var questions=allQuizes.filter { quz->quz.quizCode==param3}[0].questions
                val rcyklview = view.findViewById<RecyclerView>(R.id.recyclerViewQuizCorrectAnswers)
                rcyklview.layoutManager = LinearLayoutManager(view.context)
                val myadapter = AdapterQuizQuestionsAnswers(
                    view.context,
                    questions.await()!! as MutableList<Question>,
                    param3!!,
                    gettransactiondetailstothecurrenttransaction.await()
                )
                rcyklview.adapter = myadapter


            }
        }



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentcorrectanswersforquiz.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            fragmentcorrectanswersforquiz().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}