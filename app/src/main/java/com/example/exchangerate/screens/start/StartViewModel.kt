package com.example.exchangerate.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.Repository
import kotlinx.coroutines.launch


class StartViewModel : ViewModel() {
    private val repo = Repository()

    private val _exchangeRateDataEur = MutableLiveData<String>()
    val exchangeRateDataEur: LiveData<String>
        get() = _exchangeRateDataEur

    private val _exchangeRateDataRub = MutableLiveData<String>()
    val exchangeRateDataRub: LiveData<String>
        get() = _exchangeRateDataRub

    private val _errorCondition = MutableLiveData<Unit>()
    val errorCondition: LiveData<Unit>
        get() = _errorCondition

    fun getCurrentRate() {
        viewModelScope.launch {
            try {
                val repoResultRub = repo.getExchangeRate().body()?.rates?.RUB
                val repoResultEur = repo.getExchangeRate().body()?.rates?.EUR
                _exchangeRateDataRub.value = reduceNumbers(repoResultRub)
                _exchangeRateDataEur.value = countEur(repoResultRub, repoResultEur)
            } catch (e: Exception) {
                _errorCondition.value = Unit
            }
        }
    }

    private fun reduceNumbers(currency: Double?): String {
        return String.format("%.2f", currency)
    }

    private fun countEur(currencyRub: Double?, currencyEur: Double?): String {
        val sum = currencyRub!! / currencyEur!!
        return String.format("%.2f", sum)
    }
}
