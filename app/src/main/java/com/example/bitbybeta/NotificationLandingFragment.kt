package com.example.bitbybeta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bitbybeta.databinding.FragmentNotificationLandingBinding
import com.example.bitbybeta.databinding.FragmentQuestionBinding


class NotificationLandingFragment : Fragment() {
    private var _binding: FragmentNotificationLandingBinding? = null
    private val binding get() = _binding!!

    private var question = ""
    private var answer = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get question and answer from intent
        question = requireActivity().intent.extras?.getString("question") ?:"error: question text not found"
        answer = requireActivity().intent.extras?.getString("answer") ?:"error: answer text not found"
        //set question
        binding.cardButton.text = question

        //set up button to show answer then back to cardsetform frag
        binding.cardButton.setOnClickListener {
            //"flip" the card by changing its text
            if(binding.cardButton.text == question){
                binding.cardButton.text = answer
            } else {
                //go back to cardset view
                findNavController().navigate(R.id.CardSetFormFragment)
            }
        }
    }
}