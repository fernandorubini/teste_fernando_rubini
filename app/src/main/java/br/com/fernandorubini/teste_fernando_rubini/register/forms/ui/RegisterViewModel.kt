package br.com.fernandorubini.teste_fernando_rubini.register.forms.ui

import androidx.lifecycle.viewModelScope
import br.com.fernandorubini.teste_fernando_rubini.base.utils.SingleLiveEvent
import br.com.fernandorubini.teste_fernando_rubini.base.viewmodel.BaseViewModel
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.model.User
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase.RegisterLoginUseCase
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase.RegisterUserDataUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerLoginUseCase: RegisterLoginUseCase,
    private val registerUserDataUseCase: RegisterUserDataUseCase
) : BaseViewModel<RegisterViewState, RegisterViewAction>() {
    override val viewState = SingleLiveEvent<RegisterViewState>()

    override fun dispatchViewAction(viewAction: RegisterViewAction) = when (viewAction) {
        is RegisterViewAction.ReCaptcha -> handleReCaptcha()
        is RegisterViewAction.ButtonBack -> handleButtonBack()
        is RegisterViewAction.ButtonContinue -> handleButtonContinue(viewAction.user)
    }

    private fun handleButtonContinue(user: User) {
        viewModelScope.launch {
            registerLoginUseCase(user.email, user.password).onSuccess {
                registerUserDataUseCase(user, it).onSuccess {
                    viewState.value = RegisterViewState.NavigateSuccessScreen(user)
                }.onError {
                    viewState.value = RegisterViewState.NotifyError(it.message.orEmpty())
                }
            }.onError {
                viewState.value = RegisterViewState.NotifyError(it.message.orEmpty())
            }
        }
    }

    private fun handleButtonBack() {
        viewState.value = RegisterViewState.NavigateBack
    }

    private fun handleReCaptcha() {
        viewState.value = RegisterViewState.OpenChallenger
    }
}