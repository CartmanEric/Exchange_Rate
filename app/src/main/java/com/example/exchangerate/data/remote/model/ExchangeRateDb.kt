package com.example.exchangerate.data.remote.model

data class ExchangeRateDb(
    val rates: RatesDb,
    val time_last_update_unix: Long
)
