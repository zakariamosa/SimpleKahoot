package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface QuizDao {

    @Insert
    fun insertQuiz(quiz:Quiz)

    @Query("SELECT * FROM QUIZ WHERE quizCode LIKE :quizcode")
    fun getQuizByQuizCode(quizcode:String):List<Quiz>

    @Query("SELECT * FROM QUIZ WHERE id LIKE :teacherId")
    fun getTeacherQuizesByTeacherId(teacherId:Int):List<Quiz>

    @Transaction
    @Query("SELECT * FROM QUIZ WHERE quizCode LIKE :quizcode")
    fun getQuizWithQuestions(quizcode:String):List<QuizQuestions>
}