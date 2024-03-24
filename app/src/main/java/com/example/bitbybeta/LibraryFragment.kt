package com.example.bitbybeta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitbybeta.databinding.FragmentLibraryBinding
import java.util.Date
import kotlin.random.Random

/**
 * A simple [Fragment] subclass for the library destination in the navigation.
 */
class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    companion object {
        val cardSetModels = listOf(
            CardSetModel("History Heroes", 20, randomDate(), randomDate()),
            CardSetModel("Science Safari", 15, randomDate(), randomDate()),
            CardSetModel("Literature Legends", 25, randomDate(), randomDate()),
            CardSetModel("Math Masters", 30, randomDate(), randomDate()),
            CardSetModel("Art Adventures", 10, randomDate(), randomDate()),
            CardSetModel("Geography Geniuses", 20, randomDate(), randomDate()),
            CardSetModel("Music Mayhem", 15, randomDate(), randomDate()),
            CardSetModel("Language Legends", 25, randomDate(), randomDate()),
            CardSetModel("Tech Titans", 30, randomDate(), randomDate()),
            CardSetModel("Nature Navigators", 10, randomDate(), randomDate())
            // Add more creative names and their corresponding constructors as needed
        )

        // Function to generate a random date within a range
        private fun randomDate(): Date {
            val now = System.currentTimeMillis()
            val random = Random(now)
            val minDay = Date().time
            val maxDay = Date().time + (1000L * 60 * 60 * 24 * 365 * 5) // 5 years in milliseconds
            val randomDay = random.nextLong() % (maxDay - minDay) + minDay
            return Date(randomDay)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.CardSetRecyclerView
        val cardSetAdapter = CardSetAdapter(cardSetModels)
        recyclerView.adapter = cardSetAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //binding.buttonBack.setOnClickListener {
        //    findNavController().navigate(R.id.action_LibraryFragment_to_FirstFragment)
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
