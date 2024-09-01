package br.com.fernandorubini.teste_fernando_rubini.login.ui

sealed class LoginViewAction {
    data class ButtonContinue(
        val email: String,
        val password: String
    ) : LoginViewAction()

    data object ButtonBack : LoginViewAction()
    data object ButtonRegister : LoginViewAction()
}