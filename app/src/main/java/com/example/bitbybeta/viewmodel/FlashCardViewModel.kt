package com.example.bitbybeta.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bitbybeta.Data.AppDatabase
import com.example.bitbybeta.entity.FlashCardEntity
import com.example.bitbybeta.Data.FlashCardRepo
import kotlinx.coroutines.launch


class FlashCardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FlashCardRepo
    private val flashCardDao = AppDatabase.getDatabase(application).FlashCardDao()
    private var selectedCardSetId: Long = 0

    init {
        repository = FlashCardRepo(flashCardDao)
    }

    val allFlashCards: LiveData<List<FlashCardEntity>> by lazy {
        repository.getFlashCardsByCardSet(selectedCardSetId)
    }


    fun setCardSetId(cardSetId: Long) {
        selectedCardSetId = cardSetId
    }

    fun getFlashCardsByCardSet(cardSetId: Long): LiveData<List<FlashCardEntity>> {
        return repository.getFlashCardsByCardSet(cardSetId)
    }

    fun addFlashCard(flashCard: FlashCardEntity) {
        viewModelScope.launch {
            val result = repository.addFlashCard(flashCard)
        }
    }

    fun updateFlashCard(flashCard: FlashCardEntity) {
        viewModelScope.launch {
            val result = repository.updateFlashCard(flashCard)
        }
    }

    fun deleteFlashCard(flashCard: FlashCardEntity) {
        viewModelScope.launch {
            val result = repository.deleteFlashCard(flashCard)
        }
    }

    fun clearFlashCards(cardSetId: Long) {
        viewModelScope.launch {
            repository.clearFlashCards(cardSetId)
        }
    }

    fun getTotalFlashCards(cardSetId: Long): LiveData<Int> {
        return repository.getTotalFlashCards(cardSetId)
    }
}