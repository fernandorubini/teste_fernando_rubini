package br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request

import androidx.annotation.Keep

@Keep
data class RegisterUserDataRequest(
    val name: String,
    val cpf: String,
    val phoneNumber: String,
    val email: String,
    val picture: String = ""
)