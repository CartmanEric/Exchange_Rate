package com.example.exchangerate.screens.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.data.repository.Repository
import kotlinx.coroutines.launch


class StartViewModel : ViewModel() {
    val repo = Repository()
    val exchangeRateDataEur: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val exchangeRateDataRub: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val errorCondition: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }
    fun getCurrentRate() {
        viewModelScope.launch {
            try {
                val repoResultRub = repo.getExchangeRate().body()?.rates?.RUB
                val repoResultEur = repo.getExchangeRate().body()?.rates?.EUR
                exchangeRateDataRub.value = reduceNumbers(repoResultRub)
                exchangeRateDataEur.value = countEur(repoResultRub, repoResultEur)
            }
            catch (e: Exception){
                errorCondition.value = false
            }
        }
    }
}
private fun reduceNumbers(currency: Double?): String {
    return String.format("%.2f", currency)
}

fun countEur(currencyRub: Double?, currencyEur: Double?): String {
    val sum = currencyRub!! / currencyEur!!
    return String.format("%.2f", sum)
}

