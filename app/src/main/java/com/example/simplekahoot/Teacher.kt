package com.example.simplekahoot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher")
data class Teacher(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "Teacher Name") var teacherName:String?,
    @ColumnInfo(name = "Email") var email:String?,
    @ColumnInfo(name = "Password") var password:String?) {
}