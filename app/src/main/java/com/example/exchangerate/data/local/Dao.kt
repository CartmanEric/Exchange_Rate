package com.example.exchangerate.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangerate.data.local.model.RatesDb

@Dao
interface Dao {
    @Query("SELECT * FROM exchange_rate")
    fun getItems(): LiveData<List<RatesDb>>

    @Query("SELECT * FROM exchange_rate")
    suspend fun getItemsList(): List<RatesDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItems(items: RatesDb)

}