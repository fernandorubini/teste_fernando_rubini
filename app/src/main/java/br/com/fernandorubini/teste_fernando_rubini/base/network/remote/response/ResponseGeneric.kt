package br.com.fernandorubini.teste_fernando_rubini.base.network.remote.response

data class ResponseGeneric<T>(
    val apiVersion: String? = null,
    val transactionId: String? = null,
    val data: T?
)
