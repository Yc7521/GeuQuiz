package com.geuquiz.ui.question

import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.geuquiz.model.Question
import com.geuquiz.util.ObservableFragmentViewModel

open class AnswerViewModel : ObservableFragmentViewModel<Fragment>() {
    var index: Int = 0
    val question = MutableLiveData<Question>()

    @Bindable
    open val answer: MutableLiveData<String> = MutableLiveData<String>()

    override fun init(fragment: Fragment) {
        super.init(fragment)
        answer.value = question.value?.answer.toString()
    }

    open fun backQuestion() {
        AnswerFragmentDirections.actionAnswerToQuestion().let {
            it.index = index
            it.cheat = true
            fragment.findNavController().navigate(it)
        }
    }
}