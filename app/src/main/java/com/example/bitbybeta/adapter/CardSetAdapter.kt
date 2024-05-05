package com.example.bitbybeta.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitbybeta.R
import com.example.bitbybeta.entity.CardSetEntity
import java.text.SimpleDateFormat
import java.util.Locale

class CardSetAdapter(private val cardSetList: List<CardSetEntity>) :
    RecyclerView.Adapter<CardSetAdapter.CardSetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardset_row,
            parent, false
        )
        return CardSetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardSetViewHolder, position: Int) {
        val currentItem = cardSetList[position]

        holder.titleTextView.text = currentItem.getCardSetTitle()

        // Format start date
        val startDate = currentItem.getCardSetStartDate()
        val formattedStartDate = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(startDate)
        holder.startDateTextView.text = formattedStartDate

        // Format end date
        val endDate = currentItem.getCardSetEndDate()
        val formattedEndDate = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(endDate)
        holder.endDateTextView.text = formattedEndDate


    }

    override fun getItemCount() = cardSetList.size

    class CardSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.text_title)
        val startDateTextView: TextView = itemView.findViewById(R.id.text_start_date)
        val endDateTextView: TextView = itemView.findViewById(R.id.text_end_date)

    }
}
