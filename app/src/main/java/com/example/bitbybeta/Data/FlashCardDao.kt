package com.example.bitbybeta.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bitbybeta.entity.FlashCardEntity

@Dao
interface FlashCardDao {
    @Insert
    suspend fun insertFlashCard(flashCard: FlashCardEntity): Long

    @Delete
    suspend fun deleteFlashCard(flashCard: FlashCardEntity)

    @Update
    suspend fun updateFlashCard(flashCard: FlashCardEntity)

    @Query("SELECT * FROM flashcards WHERE cardSetId = :cardSetId")
    fun getFlashCardsByCardSetId(cardSetId: Long): LiveData<List<FlashCardEntity>>

    @Query("DELETE FROM flashcards WHERE cardSetId = :cardSetId")
    suspend fun clearFlashCards(cardSetId: Long)

    @Query("SELECT COUNT(id) FROM flashcards WHERE cardSetId = :cardSetId")
    fun countFlashCards(cardSetId: Long): LiveData<Int>
}