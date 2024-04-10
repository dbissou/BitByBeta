package com.example.bitbybeta.Data
import androidx.lifecycle.LiveData
import kotlin.collections.Set

class CardRepository(private val CardDoa: CardDao) {

    fun getCardBySet(setId: Int): LiveData<List<Card>> = CardDoa.getCardBySet(setId)

    suspend fun insert(card: Card) {
        CardDoa.insertCard(card)
    }
}
