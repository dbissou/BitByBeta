package com.example.bitbybeta

import CardSetViewModel
import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitbybeta.adapter.FlashCardAdapter
import com.example.bitbybeta.adapter.QuestionAdapter
import com.example.bitbybeta.databinding.FragmentCardSetFormBinding
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

    private val NOTIFICATION_ID = 38

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
                // Do something before the text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel
            }

            override fun afterTextChanged(s: Editable?) {
                // Do something after the text changes
            }
        })

        val dateRangeButton = binding.buttonDateRange
        dateRangeButton.setOnClickListener{
                view -> showDatePickerDialog()
        }


        // Instantiate ViewModel and pass the list of questions
        viewModel = ViewModelProvider(requireActivity()).get(CardSetViewModel::class.java)
        viewModel.setQuestions(flashCardEntities)

        // Initialize RecyclerView
        val recyclerView = binding.recyclerViewQuestions
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the list of questions from the ViewModel
        viewModel.questionsLiveData.observe(viewLifecycleOwner) { flashcards ->
            // Update RecyclerView adapter with the new list of questions
            flashCardAdapter = FlashCardAdapter(requireContext(), flashcards)
            recyclerView.adapter = flashCardAdapter
        }

        //navigate to study start on click
        binding.startStudyButton.setOnClickListener {
            if(viewModel.getTotalQuestionCount() > 0){
                findNavController().navigate(R.id.StudyStartFragment)
            }
        }

        binding.buttonNotificationDemo.setOnClickListener {
            sendNotification("Ready to study " + viewModel.getCardSetTitle() + "?")
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

    fun sendNotification(msg: String) {
        // set up intent (where notification will send you)
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val q = viewModel.getQuestion((0..<viewModel.getTotalQuestionCount()).random())
        intent.putExtra("question", q?.question)
        intent.putExtra("answer", q?.answer)

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)

        //set up builder for notification
        var builder = NotificationCompat.Builder(
            requireContext(),
            getString(R.string.notification_channel_id)
        )
            .setSmallIcon(R.drawable.cards)
            .setContentTitle("real notification")
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that fires when the user taps the notification.
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //build and send notification
        with(NotificationManagerCompat.from(requireContext())) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                //                                        grantResults: IntArray)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return@with
            }
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}