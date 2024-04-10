package com.example.bitbybeta.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitbybeta.entity.QuestionEntity
import java.util.*

class CardSetViewModel : ViewModel() {

    var cardSetTitle: String = ""
    var cardSetQuestionCount: Int? = null
        set(value) {
            field = value
        }
        get() = field

    var cardSetStartDate: Date? = null
        set(value) {
            field = value
        }
        get() = field

    var cardSetEndDate: Date? = null
        set(value) {
            field = value
        }
        get() = field

    var cards: MutableList<QuestionEntity> = mutableListOf()

    fun addQuestion(question: QuestionEntity) {
        cards.add(question)
    }

    fun removeQuestion(question: QuestionEntity) {
        cards.remove(question)
    }

    fun getTotalQuestionCount(): Int {
        return cards.size
    }

    fun getQuestion(index: Int): QuestionEntity? {
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
