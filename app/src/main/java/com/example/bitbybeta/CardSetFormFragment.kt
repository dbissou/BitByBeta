package com.example.bitbybeta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bitbybeta.viewmodel.CardSetViewModel

class CardSetFormFragment : Fragment() {

    companion object {
        fun newInstance() = CardSetFormFragment()
    }

    private lateinit var viewModel: CardSetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_set_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardSetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}