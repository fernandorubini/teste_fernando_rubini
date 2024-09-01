package br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request

import androidx.annotation.Keep

@Keep
data class RegisterLoginRequest(
    val email: String,
    val password: String,

    )