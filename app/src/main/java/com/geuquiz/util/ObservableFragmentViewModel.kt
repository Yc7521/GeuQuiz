package com.geuquiz.util

import androidx.fragment.app.Fragment

open class ObservableFragmentViewModel<T : Fragment> : ObservableViewModel() {
    @Transient
    private var _fragment: T? = null
    open val fragment: T
        get() = _fragment!!

    open fun init(fragment: T) {
        _fragment = fragment
    }
}