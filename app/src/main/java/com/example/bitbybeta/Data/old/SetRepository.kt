package com.example.bitbybeta.Data.old
import androidx.lifecycle.LiveData

class SetRepository(private val setDoa: SetDao) {

    val readAllSets: LiveData<List<Set>> = setDoa.getAllSets()

    suspend fun addSet(set: Set) {
        setDoa.addSet(set)
    }
}
