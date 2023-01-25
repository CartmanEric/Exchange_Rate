package com.example.exchangerate.data

import com.example.exchangerate.data.model.ExchangeRateDb
import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class Mapper @Inject constructor() {


    fun exchangeRateDbToRates(exchangeRate: ExchangeRateDb): Rates {
        val rub = reduceNumbers(exchangeRate.rates.RUB)
        val eur = countEur(exchangeRate.rates.RUB, exchangeRate.rates.EUR)
        return Rates(eur, rub)
    }

    private fun reduceNumbers(number: Double): String {
        return String.format("%.2f", number)
    }

    private fun countEur(currencyRub: Double, currencyEur: Double): String {
        val totalSum = currencyRub / currencyEur
        return String.format("%.2f", totalSum)
    }

}