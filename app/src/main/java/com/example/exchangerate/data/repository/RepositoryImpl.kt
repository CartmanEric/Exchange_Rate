package com.example.exchangerate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.exchangerate.data.local.Dao
import com.example.exchangerate.data.local.LocalMapper
import com.example.exchangerate.data.remote.RemoteMapper
import com.example.exchangerate.data.remote.api.RetrofitInstance
import com.example.exchangerate.domain.ExchangeRateRepository
import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localMapper: LocalMapper,
    private val remoteMapper: RemoteMapper,
    private val dao: Dao
) : ExchangeRateRepository {

    override suspend fun getExchangeRate(): Rates {
        return remoteMapper.exchangeRateDbToRates(RetrofitInstance.api.getApi())
    }

    override suspend fun getItemsList(): List<Rates> {
        return localMapper.mapListDbModelToListEntity(dao.getItemsList())
    }

    override fun getItemsLiveData(): LiveData<List<Rates>> {
        return Transformations.map(dao.getItems()) {
            localMapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun addItems(item: Rates) {
        dao.addItems(localMapper.mapEntityToDbModel(item))
    }
}