package com.geuquiz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geuquiz.dao.Questions
import com.geuquiz.model.Question

@Database(entities = [Question::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questions(): Questions
}