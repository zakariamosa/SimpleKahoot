package com.example.simplekahoot

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentcompetition.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentcompetition : Fragment() {
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
        var view=inflater.inflate(R.layout.fragment_fragmentcompetition, container, false)



        setBarChart(view)



        fitchnextquestion()








        return view
    }

    fun fitchnextquestion(){
        val p1=this.param1
        val p2=this.param2!!
        val p3=this.param3!!
        val frg=this.fragmentManager
        //playsound()
        val timer = object : CountDownTimer(9000, 1000) {
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
                //stopSound()
            }
        }
        timer.start()

    }

    private fun setBarChart(view:View) {
        val entries = ArrayList<BarEntry>()

        var quizquestions = allQuizes.filter { Quiz -> Quiz.quizCode == param3 }

        var currentQuestion=when(param2!!){
            "lastquestion"->quizquestions[0]?.questions?.get(quizquestions[0].numberOfQuestions-1)
            else->quizquestions[0]?.questions?.get(param2!!.toInt()-2)}
        var studenttransactiondetailsforthisquizandquistion=allTransactionDetails.filter { td->td.quizcode==param3 &&td.question.question==currentQuestion!!.question}
        if (studenttransactiondetailsforthisquizandquistion.size>1){
            //entries.add(BarEntry(0f, 0))
            var i:Int=0
            for (td in studenttransactiondetailsforthisquizandquistion){
                entries.add(BarEntry(td.studentcurrentscour.toFloat(), i))
                i++
            }
            /*entries.add(BarEntry(8f, 0))
            entries.add(BarEntry(2f, 1))
            entries.add(BarEntry(5f, 2))
            entries.add(BarEntry(200f, 3))
            entries.add(BarEntry(15f, 4))
            entries.add(BarEntry(19f, 5))*/

            val barDataSet = BarDataSet(entries, "Rate")

            val labels = ArrayList<String>()

            //labels.add("")

            for (td in studenttransactiondetailsforthisquizandquistion){
                labels.add(td.student.StudentName)
            }
            /*labels.add("18-Jan")
            labels.add("19-Jan")
            labels.add("20-Jan")
            labels.add("21-Jan")
            labels.add("22-Jan")
            labels.add("23-Jan")*/
            val data = BarData(labels, barDataSet)
            var barChart=view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.barChart)
            barChart.data = data // set the data and list of lables into chart

            barChart.setDescription(quizquestions[0]?.quizName)  // set the description

            //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
            //barDataSet.color = resources.getColor(R.color.colorAccent)
            //barDataSet.color = resources.getColor(R.color.colorPrimary)
            val listColors = ArrayList<Int>()
            listColors.add(Color.GREEN)
            listColors.add(Color.BLUE)
            listColors.add(Color.YELLOW)
            listColors.add(Color.CYAN)
            listColors.add(Color.BLACK)
            listColors.add(Color.DKGRAY)
            barDataSet.colors=listColors

            barChart.animateY(5000)
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentcompetition.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            fragmentcompetition().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}