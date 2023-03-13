package com.example.exchangerate.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate")
data class RatesDb(
    val EUR: String,
    val RUB: String,
    val data: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}