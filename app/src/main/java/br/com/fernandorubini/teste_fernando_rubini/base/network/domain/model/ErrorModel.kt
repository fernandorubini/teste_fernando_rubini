package br.com.fernandorubini.teste_fernando_rubini.base.network.domain.model

data class ErrorModel (
    val errorCode: String,
    val message: String,
    val detailedMessage: String
)