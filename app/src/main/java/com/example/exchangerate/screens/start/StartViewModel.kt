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

    init {
        getCurrentRate()
    }

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

    private fun reduceNumbers(oldNumbers: Double?): String {
        val newNumbers = oldNumbers ?: throw RuntimeException("There is null")
        return String.format("%.2f", newNumbers)
    }

    private fun countEur(currencyRub: Double?, currencyEur: Double?): String {
        val totalSum =
            if (currencyRub != null && currencyEur != null) {
                currencyRub / currencyEur
            } else {
                throw RuntimeException("$currencyRub and $currencyEur are empty")
            }
        return String.format("%.2f", totalSum)
    }
}
