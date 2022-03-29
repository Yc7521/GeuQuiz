package com.geuquiz.ui.question

import android.os.Bundle
import android.util.Log
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.geuquiz.BR
import com.geuquiz.R
import com.geuquiz.model.Question
import com.geuquiz.util.ObservableFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

open class QuestionViewModel : ObservableFragmentViewModel<Fragment>(), Serializable {
    var cheat = false
    private var questions = MutableLiveData<List<Question>>().apply {
        value = ArrayList()
    }
    private var currentQuestionIndex = 0
    private val currentQuestion
        get() = questions.value?.get(currentQuestionIndex) ?: Question()
    private val size: Int
        get() = questions.value?.size ?: 0
    private val end: Boolean
        get() = currentQuestionIndex >= questions.value?.size ?: 0

    @Bindable
    open val info: MutableLiveData<String> = MutableLiveData()

    @Bindable
    open val question: MutableLiveData<String> = MutableLiveData()

    @Bindable
    open val answer: MutableLiveData<String> = MutableLiveData()

    override fun init(fragment: Fragment) {
        super.init(fragment)
        question.value = currentQuestion.title
        answer.value = currentQuestion.answer.toString()
        info.value = "Question ${currentQuestionIndex + 1} of $size"
    }

    private fun showEnd() {
        fragment.apply {
            Snackbar.make(
                view!!,
                getString(R.string.str_end_info),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    open fun setQuestions(value: List<Question>) {
        questions.value = value
    }

    open fun setIndex(index: Int) {
        currentQuestionIndex = index
    }

    open fun onTrue() {
        if (end) {
            showEnd()
            return
        }
        fragment.apply {
            Snackbar.make(
                view!!,
                getString(
                    when {
                        cheat && currentQuestion.answer -> R.string.str_cheat_correct
                        currentQuestion.answer -> R.string.str_correct
                        cheat -> R.string.str_cheat_incorrect
                        else -> R.string.str_incorrect
                    }
                ),
                Snackbar.LENGTH_SHORT
            ).show()
        }
        next()
    }

    open fun onFalse() {
        if (end) {
            showEnd()
            return
        }
        fragment.apply {
            Snackbar.make(
                view!!,
                getString(
                    when {
                        cheat && !currentQuestion.answer -> R.string.str_cheat_correct
                        !currentQuestion.answer -> R.string.str_correct
                        cheat -> R.string.str_cheat_incorrect
                        else -> R.string.str_incorrect
                    }
                ),
                Snackbar.LENGTH_SHORT
            ).show()
        }
        next()
    }

    open fun next(): Boolean {
        if (end) {
            showEnd()
            return false
        }
        questions.value?.let {
            if (currentQuestionIndex < it.size) {
                cheat = false
                currentQuestionIndex++
                question.value = currentQuestion.title
                answer.value = currentQuestion.answer.toString()
                info.value = "Question ${currentQuestionIndex + 1} of $size"
                notifyPropertyChanged(BR.question)
                notifyPropertyChanged(BR.info)
            }
        }
        return true
    }

    open fun showAnswer() {
        if (end) {
            showEnd()
            return
        }
        QuestionFragmentDirections.actionQuestionToAnswer(currentQuestion, currentQuestionIndex)
            .apply {
                fragment.findNavController().navigate(this)
            }
    }

    fun onSaveInstanceState(state: Bundle) {
        state.putInt("index", currentQuestionIndex)
        Log.d("QuestionViewModel", "onSaveInstanceState")
    }

    fun onRestoreInstanceState(state: Bundle) {
        currentQuestionIndex = state.getInt("index")
        Log.d("QuestionViewModel", "onRestoreInstanceState: $currentQuestionIndex")
    }

}
