package com.example.bitbybeta

import android.app.PendingIntent
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitbybeta.adapter.CardSetAdapter
import com.example.bitbybeta.databinding.FragmentLibraryBinding
import com.example.bitbybeta.entity.CardSetEntity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
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

        val cardSetEntities = listOf(
            CardSetEntity(
                id = 1,
                cardSetTitle = "History Heroes",
                cardSetStartDate = Date(),
                cardSetEndDate = Date()
            ),
            CardSetEntity(
                id = 2,
                cardSetTitle = "Science Safari",
                cardSetStartDate = Date(),
                cardSetEndDate = Date()
            ),
            CardSetEntity(
                id = 3,
                cardSetTitle = "Literature Legends",
                cardSetStartDate = Date(),
                cardSetEndDate = Date()
            ),
            CardSetEntity(
                id = 4,
                cardSetTitle = "Math Masters",
                cardSetStartDate = Date(),
                cardSetEndDate = Date()
            ),
            CardSetEntity(
                id = 5,
                cardSetTitle = "Art Adventures",
                cardSetStartDate = Date(),
                cardSetEndDate = Date()
            )
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
        val cardSetAdapter = CardSetAdapter(cardSetEntities)
        recyclerView.adapter = cardSetAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val layoutInflater = LayoutInflater.from(requireContext()) //find the same layout inflater
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null) // get layout
        val bottomSheetDialog = BottomSheetDialog(requireActivity())
        bottomSheetDialog.setContentView(bottomSheetView);

        binding.cardsetfab.setOnClickListener {
            bottomSheetDialog.show()
        }

        val option1 = bottomSheetView.findViewById<TextView>(R.id.create_cardset)
        option1.setOnClickListener {
            // Handle option 1 click here (e.g., dismiss dialog, perform action)
            bottomSheetDialog.dismiss()
            Snackbar.make(view, "Option 1 selected!", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_LibraryFragment_to_CardSetFormFragment)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
