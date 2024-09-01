package br.com.fernandorubini.teste_fernando_rubini.login.data.response

import androidx.annotation.Keep

@Keep
data class LoginRequest(
    val email: String,
    val password: String
)