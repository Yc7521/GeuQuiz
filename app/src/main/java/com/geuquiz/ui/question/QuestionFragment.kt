package com.geuquiz.ui.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.geuquiz.databinding.FragmentQuestionBinding
import com.geuquiz.db.DbSet

class QuestionFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentQuestionBinding
    private val viewModel: QuestionViewModel by viewModels()
    private val args: QuestionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        binding.data = viewModel.apply {
            setQuestions(DbSet.questions.all ?: emptyList())
            setIndex(args.index)
            cheat = args.cheat
            init(this@QuestionFragment)
            Log.d("QuestionFragment", "index: ${args.index}")
            savedInstanceState?.let { bundle ->
                onRestoreInstanceState(bundle)
            }
            notifyChange()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }
}

