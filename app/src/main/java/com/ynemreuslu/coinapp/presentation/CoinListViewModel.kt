package com.ynemreuslu.coinapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ynemreuslu.coinapp.CoinApplication
import com.ynemreuslu.coinapp.data.CoinRepository
import com.ynemreuslu.coinapp.model.Coin
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CoinUiState {
    data class Success(var info: List<Coin>) : CoinUiState
    data object Error : CoinUiState
    data object Loading : CoinUiState
}

class CoinListViewModel(private val coinRepository: CoinRepository) : ViewModel() {
    var coinUiState: CoinUiState by mutableStateOf(CoinUiState.Loading)
        private set

    init {
        getCoinInfo()
    }

    fun getCoinInfo() {
        viewModelScope.launch {
            coinUiState = CoinUiState.Loading
            coinUiState = try {
                CoinUiState.Success(coinRepository.getCoinInfo())
            } catch (e: IOException) {
                CoinUiState.Error
            } catch (e: HttpException) {
                CoinUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CoinApplication)
                val coinRepository = application.container.coinRepository
                CoinListViewModel(coinRepository = coinRepository)
            }
        }
    }


}