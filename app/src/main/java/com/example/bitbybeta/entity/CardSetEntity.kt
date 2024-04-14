package com.example.bitbybeta.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "card_sets")
data class CardSetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    private var cardSetTitle: String?,
    private var cardSetStartDate: Date?,
    private var cardSetEndDate: Date?
) {
    val cards: MutableList<FlashCardEntity> = mutableListOf()

    // Getters and Setters for cardSetTitle
    fun getCardSetTitle(): String? {
        return cardSetTitle
    }

    fun setCardSetTitle(cardSetTitle: String) {
        this.cardSetTitle = cardSetTitle
    }

    // Getters and Setters for cardSetStartDate
    fun getCardSetStartDate(): Date? {
        return cardSetStartDate
    }

    fun setCardSetStartDate(cardSetStartDate: Date?) {
        this.cardSetStartDate = cardSetStartDate
    }

    // Getters and Setters for cardSetEndDate
    fun getCardSetEndDate(): Date? {
        return cardSetEndDate
    }

    fun setCardSetEndDate(cardSetEndDate: Date?) {
        this.cardSetEndDate = cardSetEndDate
    }

    // Other methods remain unchanged
    fun addQuestion(question: FlashCardEntity) {
        cards.add(question)
    }

    fun removeQuestion(question: FlashCardEntity) {
        cards.remove(question)
    }

    fun getTotalQuestionCount(): Int {
        return cards.size
    }

    fun getQuestion(index: Int): FlashCardEntity? {
        return if (index >= 0 && index < cards.size) {
            cards[index]
        } else {
            null
        }
    }

    fun clearQuestions() {
        cards.clear()
    }
}
