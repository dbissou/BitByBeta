package com.example.bitbybeta.entity

class CardManager {
    private val cardsBySet: MutableMap<Long, MutableList<FlashCardEntity>> = mutableMapOf()

    fun addQuestion(cardSetId: Long, question: FlashCardEntity) {
        cardsBySet.getOrPut(cardSetId) { mutableListOf() }.add(question)
    }

    fun removeQuestion(cardSetId: Long, question: FlashCardEntity) {
        cardsBySet[cardSetId]?.remove(question)
    }

    fun getTotalQuestionCount(cardSetId: Long): Int {
        return cardsBySet[cardSetId]?.size ?: 0
    }

    fun getQuestion(cardSetId: Long, index: Int): FlashCardEntity? {
        return cardsBySet[cardSetId]?.get(index)
    }

    fun clearQuestions(cardSetId: Long) {
        cardsBySet[cardSetId]?.clear()
    }

    fun getCardsForSet(cardSetId: Long): List<FlashCardEntity> {
        return cardsBySet[cardSetId] ?: emptyList()
    }
}
