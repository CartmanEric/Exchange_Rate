package com.example.exchangerate.presentation.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.domain.GetExchangeRateUseCase
import com.example.exchangerate.domain.model.CheckCondition
import com.example.exchangerate.domain.model.ExchangeRateSealed
import com.example.exchangerate.domain.model.Rates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class StartViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase
) : ViewModel() {

    init {
        getCurrentRate()
    }

    private val _exchangeRate = MutableLiveData<ExchangeRateSealed>()
    val exchangeRater: LiveData<ExchangeRateSealed>
        get() = _exchangeRate


    fun getCurrentRate() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repoResultRub = getExchangeRateUseCase.getExchangeRate().RUB
                val repoResultEur = getExchangeRateUseCase.getExchangeRate().EUR
                _exchangeRate.postValue(Rates(EUR = repoResultEur, RUB = repoResultRub))
            } catch (e: Exception) {
                _exchangeRate.postValue(CheckCondition)
            }
        }
    }
}
