package com.example.exchangerate.presentation.screens.detailed

import androidx.lifecycle.ViewModel
import com.example.exchangerate.domain.GetExchangeRateItemsLiveDataUseCase
import javax.inject.Inject

class DetailedViewModel @Inject constructor(
    private val getLiveDateList: GetExchangeRateItemsLiveDataUseCase
) : ViewModel() {

    val getList = getLiveDateList.getItemsLiveData()

}