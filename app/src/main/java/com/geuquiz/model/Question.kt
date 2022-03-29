package com.geuquiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Question(
    @field:PrimaryKey var title: String = "undefined",
    var answer: Boolean = true
) : Serializable {
    override fun toString(): String {
        return String.format("Question '%s', answer=%s}", title, answer)
    }

    companion object Builder {
        fun T(title: String): Question {
            return Question(title, true)
        }

        fun F(title: String): Question {
            return Question(title, false)
        }
    }
}
