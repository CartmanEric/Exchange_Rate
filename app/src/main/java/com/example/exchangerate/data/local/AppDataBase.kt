package com.example.exchangerate.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangerate.data.local.model.RatesDb

@Database(entities = [RatesDb::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dataBase(): Dao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "list_of_shopping.db"
        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}