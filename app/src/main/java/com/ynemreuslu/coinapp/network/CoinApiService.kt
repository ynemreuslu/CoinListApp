package com.ynemreuslu.coinapp.network

import com.ynemreuslu.coinapp.model.Coin
import retrofit2.http.GET

interface CoinApiService {
    @GET("/v1/coins")
    suspend fun getCoin(): List<Coin>

}