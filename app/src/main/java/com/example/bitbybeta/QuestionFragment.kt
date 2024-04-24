package com.example.bitbybeta

//import CardSetViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bitbybeta.databinding.FragmentQuestionBinding
import com.example.bitbybeta.entity.CardSetViewModel


class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: CardSetViewModel
    private var qNum = 0
    private var question = ""
    private var answer = ""
    private var questionAux = IntArray(1)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set up viewmodel
        sharedViewModel = ViewModelProvider(requireActivity()).get(CardSetViewModel::class.java)

        //set up aux array
        questionAux = IntArray(sharedViewModel.getTotalQuestionCount())
        for( i in questionAux.indices){
            questionAux[i] = i
        }
        questionAux.shuffle()

        //initialize question and answer
        val q = sharedViewModel.getQuestion(questionAux[0])
        question = q?.question ?:"error: question text not found"
        answer = q?.answer ?:"error: answer text not found"
        binding.cardButton.text = question

        //button logic
        binding.cardButton.setOnClickListener {
            //"flip" the card by changing its text
            if(binding.cardButton.text == question){
                binding.cardButton.text = answer
            } else {
                //pull next question from viewmodel and update values
                qNum++
                //check for end of session
                if (qNum == sharedViewModel.getStudyQuestionCount()) {
                    //go to end screen/back ot cardset view
                    qNum = 0
                    findNavController().navigate(R.id.CardSetFormFragment)
                } else {
                    //go to next question otherwise
                    val nextQuest = sharedViewModel.getQuestion(questionAux[qNum])
                    question = nextQuest?.question ?:"error: question text not found"
                    answer = nextQuest?.answer ?:"error: answer text not found"
                    binding.cardButton.text = question
                }
            }
        }
    }

    override fun onDestroyView() {
        //save settings to whatever needs to be saved
        super.onDestroyView()
        _binding = null
    }
}