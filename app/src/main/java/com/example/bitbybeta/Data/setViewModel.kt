// access all queries from the DOA
// provide data to the UI and survive config changes

package com.example.bitbybeta.Data
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class setViewModel(application: Application): AndroidViewModel(application) {
    private val readAllSets: LiveData<List<Set>>
    private val repository: SetRepository

    init {
        val setDao = CardDB.getDatabase(application).setDao()
        repository = SetRepository(setDao)
        readAllSets = repository.readAllSets
    }

    fun addSet(set: Set){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSet(set)
        }
    }
}