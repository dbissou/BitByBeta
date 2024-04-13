package com.example.bitbybeta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitbybeta.entity.FlashCardEntity
import com.example.bitbybeta.R

class FlashCardAdapter(
    private val context: Context,
    private val flashCards: List<FlashCardEntity>
) : RecyclerView.Adapter<FlashCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flashcard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flashCard = flashCards[position]

        // Bind question and answer
        holder.textViewQuestion.text = flashCard.question
        holder.textViewAnswer.text = flashCard.answer
    }

    override fun getItemCount(): Int {
        return flashCards.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQuestion: TextView = itemView.findViewById(R.id.textViewQuestion)
        val textViewAnswer: TextView = itemView.findViewById(R.id.textViewAnswer)
    }
}
