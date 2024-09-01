package br.com.fernandorubini.teste_fernando_rubini.login.ui

sealed class LoginViewEstate {
    data object NavigateHome : LoginViewEstate()
    data object NavigateBack : LoginViewEstate()
    data object NavigateRegister : LoginViewEstate()
    data class NotifyError(val message: String) : LoginViewEstate()
}