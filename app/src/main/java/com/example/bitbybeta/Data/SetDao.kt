package com.example.bitbybeta.Data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSet(set: Set): Long

    @Query("SELECT * FROM set_table")
    fun readAllSets(): LiveData<List<Set>>
}