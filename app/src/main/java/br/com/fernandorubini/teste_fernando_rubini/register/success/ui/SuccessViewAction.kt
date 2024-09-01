package br.com.fernandorubini.teste_fernando_rubini.register.success.ui

sealed class SuccessViewAction {
    data class ButtonToStart(
        val name: String?,
        val email: String?,
        val cpf: String?
    ) : SuccessViewAction()
}