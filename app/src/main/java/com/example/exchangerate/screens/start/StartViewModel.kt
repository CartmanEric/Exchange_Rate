package com.example.exchangerate.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.RepositoryImpl
import com.example.exchangerate.domain.GetExchangeRateUseCase
import kotlinx.coroutines.launch


class StartViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val getExchangeRateUseCase = GetExchangeRateUseCase(repository)


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

                val repoResultRub = getExchangeRateUseCase.getExchangeRate().rates.RUB
                val repoResultEur = getExchangeRateUseCase.getExchangeRate().rates.EUR
               _exchangeRateDataRub.value = reduceNumbers(repoResultRub)
               _exchangeRateDataEur.value = countEur(repoResultRub, repoResultEur)
            } catch (e: Exception) {
                _errorCondition.value = Unit
            }
        }

    }

    private fun reduceNumbers(number: Double): String {
        return String.format("%.2f", number)
    }

    private fun countEur(currencyRub: Double, currencyEur: Double): String {
        val totalSum = currencyRub / currencyEur
        return String.format("%.2f", totalSum)
    }
}
