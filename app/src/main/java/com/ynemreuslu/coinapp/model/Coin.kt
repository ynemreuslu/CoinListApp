package com.ynemreuslu.coinapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerialName("is_active")
    val isActive: Boolean ,
    @SerialName("is_new")
    val isNew : Boolean ,
    val type : String,
)
