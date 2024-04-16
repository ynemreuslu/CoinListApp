package com.ynemreuslu.coinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ynemreuslu.coinapp.presentation.CoinListApp
import com.ynemreuslu.coinapp.ui.theme.CoinAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinAppTheme {
                CoinListApp()
            }
        }
    }
}


