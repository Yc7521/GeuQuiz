package com.geuquiz.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.geuquiz.model.Question

@Dao
interface Questions {
    @get:Query("SELECT * FROM Question")
    val all: List<Question>?

    @Query("SELECT * FROM Question WHERE title LIKE :title")
    fun findAllByTitle(title: String): List<Question>?

    @Query("SELECT * FROM Question WHERE title = :title")
    fun findByTitle(title: String): Question?

    @Insert
    fun insertAll(vararg questions: Question)

    @Delete
    fun delete(question: Question)
}