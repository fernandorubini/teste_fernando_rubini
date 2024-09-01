package br.com.fernandorubini.teste_fernando_rubini.login.ui

import androidx.lifecycle.viewModelScope
import br.com.fernandorubini.teste_fernando_rubini.base.utils.SingleLiveEvent
import br.com.fernandorubini.teste_fernando_rubini.base.viewmodel.BaseViewModel
import br.com.fernandorubini.teste_fernando_rubini.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginViewEstate, LoginViewAction>() {

    override val viewState = SingleLiveEvent<LoginViewEstate>()
    override fun dispatchViewAction(viewAction: LoginViewAction) =
        when (viewAction) {
            is LoginViewAction.ButtonBack -> handleButtonBack()
            is LoginViewAction.ButtonRegister -> handleButtonRegister()
            is LoginViewAction.ButtonContinue -> handleButtonContinue(
                viewAction.email,
                viewAction.password
            )
        }

    private fun handleButtonRegister() {
        viewState.value = LoginViewEstate.NavigateRegister
    }

    private fun handleButtonContinue(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            loginUseCase.invoke(email, password).onSuccess {
                viewState.value = LoginViewEstate.NavigateHome
            }.onError {
                viewState.value = LoginViewEstate.NotifyError(it.message.orEmpty())
            }
        }
    }

    private fun handleButtonBack() {
        viewState.value = LoginViewEstate.NavigateBack
    }
}