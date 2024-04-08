package com.example.bitbybeta.Data
import android.adservices.appsetid.AppSetId
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {
    @Insert
    suspend fun insertCard(card: Card)

    @Query("SELECT * FROM card_table WHERE setId = :setId")
    fun getCardBySet(setId: Int): LiveData<List<Card>>
}