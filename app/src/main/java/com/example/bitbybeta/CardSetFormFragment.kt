package com.example.bitbybeta

import CardSetViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitbybeta.adapter.QuestionAdapter
import com.example.bitbybeta.databinding.FragmentCardSetFormBinding
import com.example.bitbybeta.entity.QuestionEntity


class CardSetFormFragment : Fragment() {

    private var _binding: FragmentCardSetFormBinding? = null // Declare the binding variable
    private val binding get() = _binding!! // Non-null assertion for the binding variable

    private lateinit var viewModel: CardSetViewModel
    private lateinit var questionAdapter: QuestionAdapter


    companion object {
        fun newInstance() = CardSetFormFragment()

        val questionEntities = listOf(
            QuestionEntity(
                cardSetId = 1,
                questionText = "Who was the first president of the United States?",
                answerOption1 = "George Washington",
                answerOption2 = "Thomas Jefferson",
                answerOption3 = "Abraham Lincoln",
                answerOption4 = "John Adams",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 1,
                questionText = "When was the Declaration of Independence signed?",
                answerOption1 = "1776",
                answerOption2 = "1789",
                answerOption3 = "1791",
                answerOption4 = "1801",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 2,
                questionText = "What is the chemical symbol for water?",
                answerOption1 = "H2O",
                answerOption2 = "CO2",
                answerOption3 = "O2",
                answerOption4 = "NaCl",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 2,
                questionText = "Who developed the theory of relativity?",
                answerOption1 = "Albert Einstein",
                answerOption2 = "Isaac Newton",
                answerOption3 = "Galileo Galilei",
                answerOption4 = "Stephen Hawking",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 3,
                questionText = "Who wrote 'To Kill a Mockingbird'?",
                answerOption1 = "Harper Lee",
                answerOption2 = "J.K. Rowling",
                answerOption3 = "Ernest Hemingway",
                answerOption4 = "William Shakespeare",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 3,
                questionText = "What novel features the characters Elizabeth Bennet and Mr. Darcy?",
                answerOption1 = "Pride and Prejudice",
                answerOption2 = "Jane Eyre",
                answerOption3 = "Weathering Heights",
                answerOption4 = "Sense and Sensibility",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 4,
                questionText = "What is 2 + 2?",
                answerOption1 = "4",
                answerOption2 = "5",
                answerOption3 = "6",
                answerOption4 = "7",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 4,
                questionText = "What is the square root of 16?",
                answerOption1 = "4",
                answerOption2 = "5",
                answerOption3 = "6",
                answerOption4 = "7",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 5,
                questionText = "Who painted the Mona Lisa?",
                answerOption1 = "Leonardo da Vinci",
                answerOption2 = "Vincent van Gogh",
                answerOption3 = "Pablo Picasso",
                answerOption4 = "Claude Monet",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            ),
            QuestionEntity(
                cardSetId = 5,
                questionText = "Which art movement includes artists such as Jackson Pollock and Mark Rothko?",
                answerOption1 = "Abstract Expressionism",
                answerOption2 = "Cubism",
                answerOption3 = "Impressionism",
                answerOption4 = "Surrealism",
                correctAnswerIndex = 1,
                appearances = 0,
                correctlyAnswered = 0
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardSetFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate ViewModel and pass the list of questions
        viewModel = ViewModelProvider(this).get(CardSetViewModel::class.java)
        viewModel.setQuestions(questionEntities)

        // Initialize RecyclerView
        val recyclerView = binding.recyclerViewQuestions
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the list of questions from the ViewModel
        viewModel.questionsLiveData.observe(viewLifecycleOwner) { questions ->
            // Update RecyclerView adapter with the new list of questions
            questionAdapter = QuestionAdapter(requireContext(), questions)
            recyclerView.adapter = questionAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}