package com.example.bitbybeta

import CardSetViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitbybeta.adapter.QuestionAdapter
import com.example.bitbybeta.databinding.FragmentCardSetFormBinding
import com.example.bitbybeta.entity.QuestionEntity
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.FileInputStream
import java.util.Properties


class CardSetFormFragment : Fragment() {

    private var _binding: FragmentCardSetFormBinding? = null // Declare the binding variable
    private val binding get() = _binding!! // Non-null assertion for the binding variable

    private lateinit var sharedViewModel: CardSetViewModel
    private lateinit var questionAdapter: QuestionAdapter

    //OpenAI stuff
    var url = "https://api.openai.com/v1/completions"

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
    ): View {
        _binding = FragmentCardSetFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate ViewModel and pass the list of questions
        sharedViewModel = ViewModelProvider(requireActivity()).get(CardSetViewModel::class.java)
        sharedViewModel.setQuestions(questionEntities)

        // Initialize RecyclerView
        val recyclerView = binding.recyclerViewQuestions
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the list of questions from the ViewModel
        sharedViewModel.questionsLiveData.observe(viewLifecycleOwner) { questions ->
            // Update RecyclerView adapter with the new list of questions
            questionAdapter = QuestionAdapter(requireContext(), questions)
            recyclerView.adapter = questionAdapter
        }

        //make AI response not visible until necessary
        binding.aiResponseText.visibility = View.GONE
        //AI generation
        binding.aiGenerateButton.setOnClickListener {
            binding.aiResponseText.visibility = View.VISIBLE
            // setting response tv on below line.
            binding.aiResponseText.text = "Please wait.."
            // validating text
            if (binding.promptText.text.toString().isNotEmpty()) {
                // calling get response to get the response.
                getResponse(binding.promptText.text.toString())
            } else {
                Toast.makeText(context, "Please enter your query..", Toast.LENGTH_SHORT).show()
            }
        }

        //navigate to study start on click
        binding.startStudyButton.setOnClickListener {
            if(sharedViewModel.getTotalQuestionCount() > 0){
                findNavController().navigate(R.id.StudyStartFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Functions for calling OpenAI API and obfuscating API key
    private fun getResponse(query: String) {
        val responseTV = binding.aiResponseText
        // creating a queue for request queue.
        val queue: RequestQueue = Volley.newRequestQueue(context)
        // creating a json object on below line.
        val jsonObject: JSONObject? = JSONObject()
        // adding params to json object.
        jsonObject?.put("model", "text-davinci-003")
        jsonObject?.put("prompt", query)
        jsonObject?.put("temperature", 0)
        jsonObject?.put("max_tokens", 100)
        jsonObject?.put("top_p", 1)
        jsonObject?.put("frequency_penalty", 0.0)
        jsonObject?.put("presence_penalty", 0.0)

        // on below line making json object request.
        val postRequest: JsonObjectRequest =
            // on below line making json object request.
            object : JsonObjectRequest(Method.POST, url, jsonObject,
                Response.Listener { response ->
                    // on below line getting response message and setting it to text view.
                    val responseMsg: String =
                        response.getJSONArray("choices").getJSONObject(0).getString("text")
                    responseTV.text = responseMsg
                },
                // adding on error listener
                Response.ErrorListener { error ->
                    Log.e("TAGAPI", "Error is : " + error.message + "\n" + error)
                    responseTV.text = "error contacting API"
                }) {
                override fun getHeaders(): kotlin.collections.MutableMap<kotlin.String, kotlin.String> {
                    val params: MutableMap<String, String> = HashMap()
                    // adding headers on below line.
                    params["Content-Type"] = "application/json"
                    params["Authorization"] =
                        getLocalProperty("openAI_key")!!
                    return params
                }
            }

        // on below line adding retry policy for our request.
        postRequest.setRetryPolicy(object : RetryPolicy {
            override fun getCurrentTimeout(): Int {
                return 50000
            }

            override fun getCurrentRetryCount(): Int {
                return 50000
            }

            @Throws(VolleyError::class)
            override fun retry(error: VolleyError) {
            }
        })
        // on below line adding our request to queue.
        queue.add(postRequest)
    }

    fun getLocalProperty(propertyName: String): String? {
        try {
            val localPropsFile = FileInputStream("local.properties")
            val properties = Properties()
            properties.load(localPropsFile)
            return properties.getProperty(propertyName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}