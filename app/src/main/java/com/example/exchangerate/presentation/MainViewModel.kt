package com.example.exchangerate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangerate.domain.GetExchangeRateUseCase
import com.example.exchangerate.domain.model.CheckCondition
import com.example.exchangerate.domain.model.ExchangeRateSealed
import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase
) : ViewModel() {


    private val _exchangeRate = MutableLiveData<ExchangeRateSealed>()
    val exchangeRater: LiveData<ExchangeRateSealed>
        get() = _exchangeRate


    suspend fun getCurrentRate() {
        try {
            val repoResultRub = getExchangeRateUseCase.getExchangeRate().RUB
            val repoResultEur = getExchangeRateUseCase.getExchangeRate().EUR
            _exchangeRate.value = Rates(EUR = repoResultEur, RUB = repoResultRub)
        } catch (e: Exception) {
            _exchangeRate.value = CheckCondition
        }
    }

}
