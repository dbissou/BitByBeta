package com.example.bitbybeta.Data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSet(set: Set): Long

    @Query("SELECT * FROM set_table")
    fun getAllSets(): LiveData<List<Set>>
}