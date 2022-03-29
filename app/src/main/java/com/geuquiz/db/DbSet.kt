package com.geuquiz.db

import androidx.room.Room

object DbSet {
    private lateinit var db: AppDatabase
    fun init(context: android.content.Context) {
        if (::db.isInitialized) return
        db = Room
            .databaseBuilder(context, AppDatabase::class.java, "testDb")
            .createFromAsset("database/app.db")
            .allowMainThreadQueries()
            .build()
    }

    val questions
        get() = db.questions()
}