package com.example.bitbybeta.Data

import com.example.bitbybeta.entity.CardSetEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CardSetDao {
    @Insert
    suspend fun insertCardSet(cardSet: CardSetEntity): Long
    @Update
    fun updateCardSet(cardSet: CardSetEntity)

    @Delete
    fun deleteCardSet(cardSet: CardSetEntity)

    @Query("SELECT * FROM card_sets")
    fun getAllCards(): LiveData<List<CardSetEntity>>
}