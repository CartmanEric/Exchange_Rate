package com.example.exchangerate.domain.model


data class Rates(
    val EUR: String,
    val RUB: String,
    val unixTime: Long,
    val data: String = "",
    val id: Int = UNDEFINED_ID
) {
    companion object{
        const val UNDEFINED_ID = 0
    }
}