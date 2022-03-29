package com.geuquiz.ui.home

import androidx.lifecycle.MutableLiveData
import com.geuquiz.util.ObservableFragmentViewModel

open class HomeViewModel : ObservableFragmentViewModel<HomeFragment>() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    open val text: MutableLiveData<String> = _text
}