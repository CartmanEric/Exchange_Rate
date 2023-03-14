package com.example.exchangerate.presentation.screens.start

import androidx.lifecycle.ViewModel
import com.example.exchangerate.domain.GetExchangeRateItemsLiveDataUseCase
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val getLiveDateList: GetExchangeRateItemsLiveDataUseCase
) : ViewModel() {

    val getList = getLiveDateList.getItemsLiveData()

}