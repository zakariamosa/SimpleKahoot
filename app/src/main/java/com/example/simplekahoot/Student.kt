package com.example.simplekahoot

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true) val studentId: Int,
    var StudentName:String,
    var quizCode:String="",
    var Score:Double=0.0)