package br.com.fernandorubini.teste_fernando_rubini.register.success.ui

sealed class SuccessViewEstate {
    data class NavigateHome(
        val name: String,
        val email: String,
        val cpf: String
    ) : SuccessViewEstate()
}