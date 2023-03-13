package com.example.exchangerate.data.local

import com.example.exchangerate.data.local.model.RatesDb
import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class LocalMapper @Inject constructor() {
    fun mapEntityToDbModel(rates: Rates) = RatesDb(

        EUR = rates.EUR,
        RUB = rates.RUB,
        data = rates.data,
        id = rates.id
    )

    private fun mapDbModelToEntity(ratesDb: RatesDb) =
        Rates(
            EUR = ratesDb.EUR,
            RUB = ratesDb.RUB,
            data = ratesDb.data,
            id = ratesDb.id
        )

    fun mapListDbModelToListEntity(list: List<RatesDb>) = list.map {
        mapDbModelToEntity(it)
    }
}