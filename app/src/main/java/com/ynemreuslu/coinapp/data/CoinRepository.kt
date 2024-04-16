package com.ynemreuslu.coinapp.data

import com.ynemreuslu.coinapp.model.Coin
import com.ynemreuslu.coinapp.network.CoinApiService

interface CoinRepository {
    suspend fun getCoinInfo(): List<Coin>

}

class NetworkCoinRepository(private val coinApiService: CoinApiService) : CoinRepository {
    override suspend fun getCoinInfo(): List<Coin> = coinApiService.getCoin()
}