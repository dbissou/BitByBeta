package com.example.bitbybeta

import CardSetViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitbybeta.adapter.FlashCardAdapter
import com.example.bitbybeta.adapter.QuestionAdapter
import com.example.bitbybeta.databinding.FragmentCardSetFormBinding
import com.example.bitbybeta.entity.CardSetEntity
import com.example.bitbybeta.entity.FlashCardEntity
import com.example.bitbybeta.entity.QuestionEntity
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CardSetFormFragment : Fragment() {

    private var _binding: FragmentCardSetFormBinding? = null // Declare the binding variable
    private val binding get() = _binding!! // Non-null assertion for the binding variable

    private lateinit var viewModel: CardSetViewModel
    private lateinit var flashCardAdapter: FlashCardAdapter


    companion object {
        fun newInstance() = CardSetFormFragment()

        val flashCardEntities = listOf(
            FlashCardEntity(
                cardSetId = 1,
                question = "Who was the first president of the United States?",
                answer = "George Washington"
            ),
            FlashCardEntity(
                cardSetId = 1,
                question = "When was the Declaration of Independence signed?",
                answer = "1776"
            ),
            FlashCardEntity(
                cardSetId = 2,
                question = "What is the chemical symbol for water?",
                answer = "H2O"
            ),
            FlashCardEntity(
                cardSetId = 2,
                question = "Who developed the theory of relativity?",
                answer = "Albert Einstein"
            ),
            FlashCardEntity(
                cardSetId = 3,
                question = "Who wrote 'To Kill a Mockingbird'?",
                answer = "Harper Lee"
            ),
            FlashCardEntity(
                cardSetId = 3,
                question = "What novel features the characters Elizabeth Bennet and Mr. Darcy?",
                answer = "Pride and Prejudice"
            ),
            FlashCardEntity(
                cardSetId = 4,
                question = "What is 2 + 2?",
                answer = "4"
            ),
            FlashCardEntity(
                cardSetId = 4,
                question = "What is the square root of 16?",
                answer = "4"
            ),
            FlashCardEntity(
                cardSetId = 5,
                question = "Who painted the Mona Lisa?",
                answer = "Leonardo da Vinci"
            ),
            FlashCardEntity(
                cardSetId = 5,
                question = "Which art movement includes artists such as Jackson Pollock and Mark Rothko?",
                answer = "Abstract Expressionism"
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

        val title = binding.editTextCardSetTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                viewModel.setCardSetTitle(s.toString())
            }
        })

        val dateRangeButton = binding.buttonDateRange
        dateRangeButton.setOnClickListener{
                view -> showDatePickerDialog()
        }


        // Instantiate ViewModel and pass the list of questions
        viewModel = ViewModelProvider(this).get(CardSetViewModel::class.java)
        viewModel.setFlashCards(flashCardEntities)

        // Initialize RecyclerView
        val recyclerView = binding.recyclerViewQuestions
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val onDeleteClickListener: (FlashCardEntity) -> Unit = { flashCard ->
            // Implement the logic to delete the flash card
            // For example, you can call a ViewModel function to delete the flash card
            viewModel.removeFlashCard(flashCard)
        }

        // Observe the list of questions from the ViewModel
        viewModel.flashcardsLiveData.observe(viewLifecycleOwner) { flashcards ->
            // Update RecyclerView adapter with the new list of questions
            flashCardAdapter = FlashCardAdapter(requireContext(), flashcards, onDeleteClickListener)
            recyclerView.adapter = flashCardAdapter
        }



        val questionEditText = binding.questionEditText
        val answerEditText = binding.answerEditText

        // Listen for changes in the question EditText field
        questionEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setQuestion(s.toString()) // Update ViewModel with the entered question
            }
        })

        // Listen for changes in the answer EditText field
        answerEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setAnswer(s.toString()) // Update ViewModel with the entered answer
            }
        })


        val saveQuestionButton = binding.saveButton

        saveQuestionButton.setOnClickListener {
            // Retrieve the question and answer from the ViewModel
            val question = viewModel.getQuestion()
            val answer = viewModel.getAnswer()

            if(question != null && answer != null){
                // Create a new FlashCardEntity object with the entered question and answer
                val flashCardEntity = FlashCardEntity(cardSetId = 0, question = question, answer = answer)

                // Add the new FlashCardEntity to the ViewModel
                viewModel.addFlashCard(flashCardEntity)

                // Clear the EditText fields after saving
                questionEditText.text?.clear()
                answerEditText.text?.clear()

            }


        }

    }

    private fun showDatePickerDialog() {
        // Creating a MaterialDatePicker builder for selecting a date range
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        builder.setTitleText("Select a date range")

        // Building the date picker dialog
        val datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener { selection ->
            // Retrieving the selected start and end dates
            val startDate = Date(selection.first)
            val endDate = Date(selection.second)

            // Formatting the selected dates as strings
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val startDateString = sdf.format(startDate)
            val endDateString = sdf.format(endDate)

            viewModel.setCardSetEndDate(startDate)
            viewModel.setCardSetEndDate(endDate)

            // Creating the date range string
            val selectedDateRange = "$startDateString - $endDateString"

            // Displaying the selected date range in the TextView
            val selectedDate = binding.textViewSelectedDate
            selectedDate.text = selectedDateRange
        }

        // Showing the date picker dialog
        datePicker.show(childFragmentManager, "DATE_PICKER")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}