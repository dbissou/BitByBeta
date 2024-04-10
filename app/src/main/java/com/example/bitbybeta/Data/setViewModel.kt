package com.example.bitbybeta.Data
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class setViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Set>>
    private val repository: SetRepository

    init {
        val SetDao = CardDB.getDatabase(application).SetDao()
        repository = SetRepository(SetDao)
        readAllData = repository.readAllSets
    }

    fun addSet(set: Set){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSet(set)
        }
    }
}