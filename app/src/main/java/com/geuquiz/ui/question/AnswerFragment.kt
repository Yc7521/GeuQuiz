package com.geuquiz.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.geuquiz.databinding.FragmentAnswerBinding

class AnswerFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentAnswerBinding
    private val viewModel: AnswerViewModel by viewModels()
    private val args: AnswerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnswerBinding.inflate(inflater, container, false)
        binding.data = viewModel.apply {
            question.value = args.question
            index = args.index
            init(this@AnswerFragment)
            notifyChange()
        }

        return binding.root
    }

}