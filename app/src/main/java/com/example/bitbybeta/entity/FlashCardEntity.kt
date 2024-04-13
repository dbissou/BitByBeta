package com.example.bitbybeta.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class FlashCardEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var cardSetId: Long,
    var question: String,
    var answer: String
)
