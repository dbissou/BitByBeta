package com.example.bitbybeta.Data
import androidx.lifecycle.LiveData

class CardRepository(private val cardDoa: CardDao) {

    fun getCardBySet(setId: Int): LiveData<List<Card>> = cardDoa.getCardBySet(setId)

    suspend fun insert(card: Card) {
        cardDoa.insertCard(card)
    }
}
