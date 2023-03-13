package com.example.exchangerate.presentation.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangerate.domain.AddExchangeRateItemUseCase
import com.example.exchangerate.domain.GetExchangeRateItemListUseCase
import com.example.exchangerate.domain.GetExchangeRateRemoteUseCase
import com.example.exchangerate.domain.model.ConditionError
import com.example.exchangerate.domain.model.ConditionSuccess
import com.example.exchangerate.domain.model.ExchangeRateSealed
import com.example.exchangerate.domain.model.Rates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val getExchangeRateRemoteUseCase: GetExchangeRateRemoteUseCase,
    private val getList: GetExchangeRateItemListUseCase,
    private val addItem: AddExchangeRateItemUseCase
) : ViewModel() {


    private val _exchangeRate = MutableLiveData<ExchangeRateSealed>()
    val exchangeRater: LiveData<ExchangeRateSealed>
        get() = _exchangeRate


    suspend fun getAndSetData() {
        try {
            withContext(Dispatchers.IO) {
                val repoResultRub = getExchangeRateRemoteUseCase.getRemoteExchangeRate().RUB
                val repoResultEur = getExchangeRateRemoteUseCase.getRemoteExchangeRate().EUR
                val result =
                    Rates(EUR = repoResultEur, RUB = repoResultRub, data = getCurrentData())
                getList.getItemsList().find { result.data == it.data } ?: addItem.addItems(result)
                _exchangeRate.postValue(ConditionSuccess)
            }
        } catch (e: Exception) {
            _exchangeRate.value = ConditionError
        }

    }

    suspend fun checkIsListEmpty() = getList.getItemsList().isNotEmpty()


    private fun getCurrentData(): String {
        val sdf = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        return sdf.format(Date())
    }

}
