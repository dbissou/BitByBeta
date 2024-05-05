package com.example.bitbybeta

import CardSetViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bitbybeta.databinding.FragmentStudyStartBinding


class StudyStartFragment: Fragment() {
    private var _binding: FragmentStudyStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: CardSetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudyStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set up viewmodel
        sharedViewModel = ViewModelProvider(requireActivity()).get(CardSetViewModel::class.java)

        //on press, save amount of questions for session and go to question fragment
        binding.startButton.setOnClickListener {
            val num = Integer.parseInt(binding.questionNumber.text.toString())
            if(num > 0 && num <= sharedViewModel.getTotalFlashCardCount()){
                sharedViewModel.setStudyQuestionCount(num)
                findNavController().navigate(R.id.QuestionFragment)
            }
        }
    }

    override fun onDestroyView() {
        //save settings to whatever needs to be saved
        super.onDestroyView()
        _binding = null
    }
}