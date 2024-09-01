package br.com.fernandorubini.teste_fernando_rubini.base.network.remote

import androidx.annotation.Keep
import retrofit2.Response
import java.io.IOException

const val UNEXPECTED = "333"


@Keep
data class ErrorResponse(
    val statusCode: Int? = null,
    val key: String? = null,
    val code: String? = null,
    override val message: String? = null,
    val detailedMessage: String? = null,
    val module: String? = null,
    val messageLogin: String? = null,
    val responseCode: Int? = null,
    val response: Response<*>? = null
) : IOException()
