package com.example.simplekahoot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TeacherDao {

    @Insert
    fun insertTeacher(teacher:Teacher)

    @Query("SELECT * FROM Teacher WHERE email LIKE :teacheremail AND Password Like :password")
    fun getTeacherByEmailAndPassword(teacheremail:String, password:String):List<Teacher>

    @Query("SELECT * FROM Teacher")
    fun getAllTeachers():List<Teacher>
}