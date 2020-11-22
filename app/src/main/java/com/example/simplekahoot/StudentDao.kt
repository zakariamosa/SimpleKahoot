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
}