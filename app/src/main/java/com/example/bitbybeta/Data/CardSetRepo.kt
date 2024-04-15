// abstracts the access to multiple data sources (best practice)

package com.example.bitbybeta.Data

import androidx.lifecycle.LiveData
import com.example.bitbybeta.entity.CardSetEntity

class CardSetRepo(private val cardSetDao: CardSetDao) {
    val readAllData: LiveData<List<CardSetEntity>> = cardSetDao.getAllCards()

    suspend fun addCardSet(cardSet: CardSetEntity): Result<Unit>{
        return try {
            cardSetDao.insertCardSet(cardSet)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateCardSet(cardSet: CardSetEntity): Result<Unit>{
        return try {
            cardSetDao.updateCardSet(cardSet)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteCardSet(cardSet: CardSetEntity): Result<Unit>{
        return try {
            cardSetDao.deleteCardSet(cardSet)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}