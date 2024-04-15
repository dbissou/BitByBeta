package com.example.bitbybeta.Data
import DateConverter
import com.example.bitbybeta.entity.CardSetEntity
import com.example.bitbybeta.entity.FlashCardEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import  androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CardSetEntity::class, FlashCardEntity::class], version = 1, exportSchema = false) // set to false as we won't be needing to track schema version hist in our proj
@TypeConverters(DateConverter::class)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun CardSetDao(): CardSetDao
    //abstract fun cardDao(): CardDao

    companion object{
        // allow writes to be made visible to other threads
        @Volatile
        private var INSTANCE: AppDatabase ? = null

        // return our instance, if it's null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase ::class.java,
                    "app_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}