package com.example.simplekahoot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true) val questionId: Int,
    @ColumnInfo(name = "question") var question: String,
    @ColumnInfo(name = "Alternative 1") var alternativeAnswer1: String,
    @ColumnInfo(name = "Alternative 2") var alternativeAnswer2: String,
    @ColumnInfo(name = "Alternative 3") var alternativeAnswer3: String,
    @ColumnInfo(name = "Alternative 4") var alternativeAnswer4: String,
    @ColumnInfo(name = "Right Answer") var rightAnswer:Int,
    var quizId: Int
)