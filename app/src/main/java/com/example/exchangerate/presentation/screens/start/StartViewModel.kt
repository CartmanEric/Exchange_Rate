package com.example.exchangerate.presentation.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.domain.GetExchangeRateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class StartViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase
) : ViewModel() {


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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repoResultRub = getExchangeRateUseCase.getExchangeRate().RUB
                val repoResultEur = getExchangeRateUseCase.getExchangeRate().EUR
                _exchangeRateDataRub.postValue(repoResultRub)
                _exchangeRateDataEur.postValue(repoResultEur)
            } catch (e: Exception) {
                _errorCondition.postValue(Unit)
            }
        }

    }

}
