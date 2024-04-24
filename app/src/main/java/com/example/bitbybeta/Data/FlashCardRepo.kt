package com.example.bitbybeta.Data

import androidx.lifecycle.LiveData
import com.example.bitbybeta.entity.FlashCardEntity
import com.example.bitbybeta.entity.CardSetEntity

class FlashCardRepo(private val flashCardDao: FlashCardDao) {
    // LiveData observing all flashcards for a specific card set
    fun getFlashCardsByCardSet(cardSetId: Long): LiveData<List<FlashCardEntity>> {
        return flashCardDao.getFlashCardsByCardSetId(cardSetId)
    }

    suspend fun addFlashCard(flashCard: FlashCardEntity): Result<Unit> {
        return try {
            flashCardDao.insertFlashCard(flashCard)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateFlashCard(flashCard: FlashCardEntity): Result<Unit> {
        return try {
            flashCardDao.updateFlashCard(flashCard)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteFlashCard(flashCard: FlashCardEntity): Result<Unit> {
        return try {
            flashCardDao.deleteFlashCard(flashCard)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun clearFlashCards(cardSetId: Long) {
        flashCardDao.clearFlashCards(cardSetId)
    }

    fun getTotalFlashCards(cardSetId: Long): LiveData<Int> {
        return flashCardDao.countFlashCards(cardSetId)
    }
}
