package com.example.simplekahoot

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


data class QuizQuestions(
    @Embedded val quiz:Quiz,
    @Relation(
        parentColumn = "quizId",
        entityColumn = "quizId"
    )
    val questions:List<Question>
)