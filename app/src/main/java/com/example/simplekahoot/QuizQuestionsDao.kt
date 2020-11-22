package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizQuestionsDao {

    @Query("SELECT * FROM quizquestions WHERE quizId LIKE :quizId")
    fun getAllQuestionsToOneQuiz(quizId:Int):List<QuizQuestions>
}