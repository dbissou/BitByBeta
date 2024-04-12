package com.example.bitbybeta.Data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import  androidx.room.RoomDatabase

@Database(entities = [Set::class, Card::class], version = 1, exportSchema = false) // set to false as we won't be needing to track schema version hist in our proj
abstract class CardDB : RoomDatabase() {
    abstract fun setDao(): SetDao
    abstract fun cardDao(): CardDao

    companion object{
        // allow writes to be made visible to other threads
        @Volatile
        private var INSTANCE: CardDB? = null

        // return our instance, if it's null
        fun getDatabase(context: Context): CardDB{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDB::class.java,
                    "card_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}