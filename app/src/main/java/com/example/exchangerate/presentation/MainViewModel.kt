package com.example.exchangerate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangerate.domain.GetExchangeRateUseCase
import com.example.exchangerate.domain.model.CheckCondition
import com.example.exchangerate.domain.model.ExchangeRateSealed
import com.example.exchangerate.domain.model.Rates
import kotlinx.coroutines.*
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase
) : ViewModel() {


    private val _exchangeRate = MutableLiveData<ExchangeRateSealed>()
    val exchangeRater: LiveData<ExchangeRateSealed>
        get() = _exchangeRate

    private val _checkFinishCoroutine = MutableLiveData<Unit>()
    val checkFinishCoroutine: LiveData<Unit>
        get() = _checkFinishCoroutine


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _exchangeRate.value = CheckCondition
    }
    private val job = SupervisorJob()
    private val customCoroutine = CoroutineScope(Dispatchers.Main + job + exceptionHandler)
    suspend fun getCurrentRate() {
        val startCoroutine = customCoroutine.launch {
            val repoResultRub = getExchangeRateUseCase.getExchangeRate().RUB
            val repoResultEur = getExchangeRateUseCase.getExchangeRate().EUR
            _exchangeRate.value = Rates(EUR = repoResultEur, RUB = repoResultRub)
        }
        customCoroutine.launch {
            startCoroutine.join()
            _checkFinishCoroutine.value = Unit
        }
    }

}

