package com.ynemreuslu.coinapp

import android.app.Application
import com.ynemreuslu.coinapp.data.AppContainer
import com.ynemreuslu.coinapp.data.DefaultAppContainer

class CoinApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}