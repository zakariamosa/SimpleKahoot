package com.example.simplekahoot

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView

class AdapterQuizQuestionsAnswers(val context: Context, val questions:MutableList<Question>,val quizcode:String):RecyclerView.Adapter<AdapterQuizQuestionsAnswers.ViewHolder>() {


    val layoutInflator= LayoutInflater.from(context)

    var studentanswer:Int =0


    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=layoutInflator.inflate(R.layout.list_quiz_correct_answer,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val qustn=questions[position]
        holder.txtqustion.text=qustn.question
        holder.alternative1.text=qustn.alternativeAnswer1
        holder.alternative2.text=qustn.alternativeAnswer2
        holder.alternative3.text=qustn.alternativeAnswer3
        holder.alternative4.text=qustn.alternativeAnswer4


        when(qustn.rightAnswer){
            1->{holder.alternative1.setBackgroundColor(Color.GREEN)}
            2->{holder.alternative2.setBackgroundColor(Color.GREEN)}
            3->{holder.alternative3.setBackgroundColor(Color.GREEN)}
            4->{holder.alternative4.setBackgroundColor(Color.GREEN)}
        }


        studentanswer=allTransactionDetails.filter { td->td.quizcode==quizcode && td.question.question== questions[position].question&&td.student.StudentName== currentStudent.StudentName}[0]?.studentanswer
        when(studentanswer){
            1->{holder.txtStudentAnswer.setText(qustn.alternativeAnswer1)}
            2->{holder.txtStudentAnswer.setText(qustn.alternativeAnswer2)}
            3->{holder.txtStudentAnswer.setText(qustn.alternativeAnswer3)}
            4->{holder.txtStudentAnswer.setText(qustn.alternativeAnswer4)}
            else->{holder.txtStudentAnswer.setText("Did not answer")}
        }

    }
    inner class ViewHolder(myView: View):RecyclerView.ViewHolder(myView){
        val txtqustion=myView.findViewById<TextView>(R.id.txtTeacherOriginalQuestion)
        val alternative1=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer1)
        val alternative2=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer2)
        val alternative3=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer3)
        val alternative4=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer4)
        val txtStudentAnswer=myView.findViewById<TextView>(R.id.txtStudentAnswer)






    }
}