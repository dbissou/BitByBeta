package com.example.bitbybeta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bitbybeta.entity.QuestionEntity
import com.example.bitbybeta.R

class QuestionAdapter(
    private val context: Context,
    private val questions: List<QuestionEntity>
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]

        // Bind question text
        holder.textViewQuestion.text = question.getQuestionText()

        // Bind answer options
        holder.textViewOption1.text = question.getAnswerOption1()
        holder.textViewOption2.text = question.getAnswerOption2()
        holder.textViewOption3.text = question.getAnswerOption3()
        holder.textViewOption4.text = question.getAnswerOption4()

        // Highlight the correct answer
        val correctAnswerIndex = question.getCorrectAnswerIndex()
        when (correctAnswerIndex) {
            1 -> highlightCorrectAnswer(holder.textViewOption1)
            2 -> highlightCorrectAnswer(holder.textViewOption2)
            3 -> highlightCorrectAnswer(holder.textViewOption3)
            4 -> highlightCorrectAnswer(holder.textViewOption4)
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    private fun highlightCorrectAnswer(textView: TextView) {
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.correct_answer_background))
        // You can also change text color or add an icon here if needed
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQuestion: TextView = itemView.findViewById(R.id.textViewQuestion)
        val textViewOption1: TextView = itemView.findViewById(R.id.textViewOption1)
        val textViewOption2: TextView = itemView.findViewById(R.id.textViewOption2)
        val textViewOption3: TextView = itemView.findViewById(R.id.textViewOption3)
        val textViewOption4: TextView = itemView.findViewById(R.id.textViewOption4)
    }
}
