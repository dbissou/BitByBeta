// access all queries from the DOA
// provide data to the UI and survive config changes

package com.example.bitbybeta.Data
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.switchMap
//import androidx.lifecycle.Transformations

class cardViewModel(application: Application): AndroidViewModel(application) {
    private val setId = MutableLiveData<Int>() // we need to observe cards of a specific set t.f. MutableLiveData
    private val repository: CardRepository

    init {
        val cardDao = CardDB.getDatabase(application).cardDao()
        repository = CardRepository(cardDao)
    }

    val cards: LiveData<List<Card>> = setId.switchMap { id ->
        repository.getCardBySet(id)
    }

    /**
     * Call this method to set the current study set ID and trigger the LiveData to update.
     * @param setId the ID of the study set whose cards should be fetched.
     */
    fun setSetId(setId: Int) {
        this.setId.value = setId
    }

    /**
     * Insert a new card into the database.
     * @param card the Card object to be inserted.
     */
    fun insert(card: Card) = viewModelScope.launch {
        repository.insert(card)
    }
}