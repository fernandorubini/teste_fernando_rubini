package br.com.fernandorubini.teste_fernando_rubini.register.forms.ui

import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.model.User

sealed class RegisterViewAction {
    data object ReCaptcha : RegisterViewAction()
    data class ButtonContinue(val user: User) : RegisterViewAction()
    data object ButtonBack : RegisterViewAction()
}