package com.example.bitbybeta.Data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "set_table")
data class Set (
    @PrimaryKey(autoGenerate = true) val setId: Int, // the set that will contain multiple cards of a given subj
    val title: String // title of the set
)
