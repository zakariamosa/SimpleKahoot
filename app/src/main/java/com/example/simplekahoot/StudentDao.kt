package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student:Student)

    @Query("SELECT * FROM student WHERE StudentName LIKE :studentName AND quizCode LIKE :quizCode")
    fun getStudentByNameAndQuizCode(studentName:String,quizCode:String):List<Student>

    @Query("UPDATE STUDENT SET Score= :latestScore WHERE studentId= :studentId AND quizCode= :quizCode")
    fun updateStudentScore(latestScore:Double, studentId:Int, quizCode:String)

    @Query("SELECT * FROM student WHERE quizCode LIKE :quizCode")
    fun getStudentsByQuizCode(quizCode:String):List<Student>
}