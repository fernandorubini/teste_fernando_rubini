package br.com.fernandorubini.teste_fernando_rubini.register.forms.ui

import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.model.User

sealed class RegisterViewState {
    data object OpenChallenger : RegisterViewState()
    data class NavigateSuccessScreen(val user: User) : RegisterViewState()
    data object NavigateBack : RegisterViewState()
    data class NotifyError(val message: String) : RegisterViewState()
}