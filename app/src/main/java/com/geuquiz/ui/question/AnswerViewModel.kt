package com.geuquiz.ui.question

import android.util.Log
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.geuquiz.R
import com.geuquiz.model.Question
import com.geuquiz.util.ObservableFragmentViewModel

open class AnswerViewModel : ObservableFragmentViewModel<Fragment>() {
    var index: Int = 0
    val question = MutableLiveData<Question>()
    private var state: Int = 0
    private lateinit var ans: String
    private lateinit var showAns: String
    private lateinit var back: String
    private var cheat = false

    @Bindable
    open val answer: MutableLiveData<String> = MutableLiveData<String>()

    @Bindable
    open val tip: MutableLiveData<String> = MutableLiveData<String>()

    override fun init(fragment: Fragment) {
        super.init(fragment)
        ans = question.value?.answer.toString()
        showAns = fragment.getString(R.string.str_show_ans)
        back = fragment.getString(R.string.str_back_to_question)
        tip.value = showAns
    }

    open fun backQuestion() {
        when (state) {
            0 -> {
                answer.value = ans
                tip.value = back
                cheat = true
                notifyChange()
            }
            else -> {
                jump()
            }
        }
        state++
    }

    open fun jump() {
        AnswerFragmentDirections.actionAnswerToQuestion().let {
            Log.d("AnswerViewModel", "index: $index; cheat: $cheat")
            it.index = index
            it.cheat = cheat
            fragment.findNavController().navigate(it)
        }
    }

}