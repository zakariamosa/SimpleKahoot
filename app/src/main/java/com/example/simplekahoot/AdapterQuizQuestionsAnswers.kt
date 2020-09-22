package com.example.simplekahoot

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView

class AdapterQuizQuestionsAnswers(val context: Context, val questions:MutableList<Question>):RecyclerView.Adapter<AdapterQuizQuestionsAnswers.ViewHolder>() {


    val layoutInflator= LayoutInflater.from(context)

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
    }
    inner class ViewHolder(myView: View):RecyclerView.ViewHolder(myView){
        val txtqustion=myView.findViewById<TextView>(R.id.txtTeacherOriginalQuestion)
        val alternative1=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer1)
        val alternative2=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer2)
        val alternative3=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer3)
        val alternative4=myView.findViewById<TextView>(R.id.txtViewQuestionAlternativeAnswer4)
    }
}