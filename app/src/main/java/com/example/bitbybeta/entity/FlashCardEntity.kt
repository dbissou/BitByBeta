package com.example.bitbybeta.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "flashcards",
    foreignKeys = [
        ForeignKey(
            entity = CardSetEntity::class,
            parentColumns = ["id"],
            childColumns = ["cardSetId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class FlashCardEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var cardSetId: Long,
    var question: String,
    var answer: String
)
