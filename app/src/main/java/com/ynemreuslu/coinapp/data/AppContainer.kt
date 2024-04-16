package com.ynemreuslu.coinapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ynemreuslu.coinapp.network.CoinApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val coinRepository: CoinRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://api.coinpaprika.com/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val retrofitServices: CoinApiService by lazy {
        retrofit.create(CoinApiService::class.java)
    }
    override val coinRepository: CoinRepository
        get() = NetworkCoinRepository(retrofitServices)
}