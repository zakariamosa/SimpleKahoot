package com.example.simplekahoot

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Teacher::class, Question::class, Quiz::class, Student::class, Transaction::class,TransactionDetails::class),version = 7)
abstract class AppDatabase:RoomDatabase() {
    abstract val teacherDao : TeacherDao
    abstract val quizDao : QuizDao
    abstract val questionDao : QuestionDao
    abstract val studentDao : StudentDao
    abstract val transactionDao : TransactionDao
    abstract val transactionDetailsDao : TransactionDetailsDao
    companion object{
        private var INSTANCE:AppDatabase?=null

        fun  getInstance(context: Context):AppDatabase{
            synchronized(this){
                var instance=INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "quiz_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }



                return instance
            }
        }
    }
}