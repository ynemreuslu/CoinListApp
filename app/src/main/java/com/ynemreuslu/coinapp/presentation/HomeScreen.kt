package com.ynemreuslu.coinapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ynemreuslu.coinapp.R
import com.ynemreuslu.coinapp.model.Coin
import com.ynemreuslu.coinapp.ui.theme.CoinAppTheme


@Composable
fun HomeScreen(
    coinUiState: CoinUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)

) {

    when (coinUiState) {
        is CoinUiState.Error -> ErrorScreen(retryAction, modifier)
        is CoinUiState.Success -> SuccessScreen(coinUiState.info, contentPadding)
        is CoinUiState.Loading -> LoadingScreen()
    }

}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Failed to load", modifier = Modifier.padding(16.dp))
        TextButton(onClick = retryAction) {
            Text(text = "Retry")
        }
    }

}

@Composable
fun LoadingScreen() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = Color.Black)
    }
}

@Composable
fun SuccessScreen(
    coin: List<Coin>, contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = contentPadding) {
            items(coin) { coin ->
                CoinListItem(coin = coin)
            }
        }
    }
}

@Composable
fun CoinListItem(
    coin: Coin
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    CoinAppTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    CoinAppTheme {
        ErrorScreen(retryAction = { /*TODO*/ })
    }
}

