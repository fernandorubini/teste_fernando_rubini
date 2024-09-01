package br.com.fernandorubini.teste_fernando_rubini.home.domain.model

import androidx.annotation.Keep

@Keep
data class ProductModel(
    val imageURL: String,
    val name: String,
    val description: String
)