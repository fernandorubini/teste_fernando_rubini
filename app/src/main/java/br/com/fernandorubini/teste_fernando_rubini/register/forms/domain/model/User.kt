package br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.model

import androidx.annotation.Keep


@Keep
data class User(
    val name: String,
    val cpf: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val picture: String = ""
)