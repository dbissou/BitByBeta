package com.example.bitbybeta.Data
import androidx.lifecycle.LiveData

class SetRepository(private val setDoa: SetDao) {

    val readAllSets: LiveData<List<Set>> = setDoa.readAllSets()

    suspend fun addSet(set: Set) {
        setDoa.addSet(set)
    }
}
