package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert
    fun insertQuestion(question:Question)

    @Query("SELECT * FROM Question WHERE quizId LIKE :quizId")
    fun getAllQuestionsTillSpecificQuiz(quizId: Int):List<Question>
}