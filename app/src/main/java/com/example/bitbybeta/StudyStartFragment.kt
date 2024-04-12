package com.example.bitbybeta

import CardSetViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        
    }

    override fun onDestroyView() {
        //save settings to whatever needs to be saved
        super.onDestroyView()
        _binding = null
    }
}