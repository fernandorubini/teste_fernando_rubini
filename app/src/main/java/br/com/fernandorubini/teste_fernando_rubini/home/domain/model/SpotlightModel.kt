package br.com.fernandorubini.teste_fernando_rubini.home.domain.model

import androidx.annotation.Keep

@Keep
data class SpotlightModel(
    val bannerURL: String,
    val name: String,
    val description: String
)