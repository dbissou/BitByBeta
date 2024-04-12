package com.example.bitbybeta.Data
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.ZonedDateTime
import java.util.Date

@Entity(tableName = "card_table",
    foreignKeys = [ForeignKey(entity = Set::class,
        parentColumns = arrayOf("setId"),
        childColumns = arrayOf("setId"),
        onDelete = ForeignKey.CASCADE)])
data class Card (
    @PrimaryKey val cardId: Int,
    val question: String,
    val answer: String,
    val totalQuestions: Int,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val setId: Int,
)