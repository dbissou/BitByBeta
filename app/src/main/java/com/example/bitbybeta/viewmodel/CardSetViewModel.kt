// access all queries from the Dao
// communication from Repo to UI
package com.example.bitbybeta.entity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bitbybeta.Data.AppDatabase
import com.example.bitbybeta.Data.CardSetRepo
import java.util.Date

class CardSetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CardSetRepo
    private val readAllData: LiveData<List<CardSetEntity>>

    init {
        val cardSetDao = AppDatabase.getDatabase(application).CardSetDao()
        repository = CardSetRepo(cardSetDao)
        readAllData = repository.readAllData
    }

    // MutableLiveData to hold the list of questions
    private val _questionsLiveData = MutableLiveData<List<FlashCardEntity>>()

    // Expose LiveData to observe the list of questions
    val questionsLiveData: LiveData<List<FlashCardEntity>>
        get() = _questionsLiveData

    // MutableList to hold the list of questions
    private val cards = mutableListOf<FlashCardEntity>()

    // ViewModel properties to mimic the data structure of CardSetEntity
    private var cardSetTitle: String? = null
    private var cardSetStartDate: Date? = null
    private var cardSetEndDate: Date? = null

    // Function to get cardSetTitle
    fun getCardSetTitle(): String? {
        return cardSetTitle
    }

    // Function to set cardSetTitle
    fun setCardSetTitle(title: String) {
        cardSetTitle = title
    }

    // Function to get cardSetStartDate
    fun getCardSetStartDate(): Date? {
        return cardSetStartDate
    }

    // Function to set cardSetStartDate
    fun setCardSetStartDate(startDate: Date?) {
        cardSetStartDate = startDate
    }

    // Function to get cardSetEndDate
    fun getCardSetEndDate(): Date? {
        return cardSetEndDate
    }

    // Function to set cardSetEndDate
    fun setCardSetEndDate(endDate: Date?) {
        cardSetEndDate = endDate
    }

    //variable to hold number of questions for study session; defaults to max
    private var studyQuestionCount = cards.size

    // Function to add a question to the list
    fun addQuestion(question: FlashCardEntity) {
        cards.add(question)
        // Notify observers that the list has been updated
        _questionsLiveData.value = cards.toList()
    }

    // Function to remove a question from the list
    fun removeQuestion(question: FlashCardEntity) {
        cards.remove(question)
        // Notify observers that the list has been updated
        _questionsLiveData.value = cards.toList()
    }

    // Function to get the total count of questions
    fun getTotalQuestionCount(): Int {
        return cards.size
    }

    // Function to get a question at a specific index
    fun getQuestion(index: Int): FlashCardEntity? {
        return if (index >= 0 && index < cards.size) {
            cards[index]
        } else {
            null
        }
    }

    // Function to set the list of questions
    fun setQuestions(questions: List<FlashCardEntity>) {
        cards.clear()
        cards.addAll(questions)
        // Notify observers that the list has been updated
        _questionsLiveData.value = cards.toList()
    }

    // Function to clear the list of questions
    fun clearQuestions() {
        cards.clear()
        // Notify observers that the list has been updated
        _questionsLiveData.value = emptyList()
    }

    //get and set question count for specific study session
    fun setStudyQuestionCount(n: Int) {
        studyQuestionCount = n
    }

    fun getStudyQuestionCount(): Int {
        return studyQuestionCount
    }
}
