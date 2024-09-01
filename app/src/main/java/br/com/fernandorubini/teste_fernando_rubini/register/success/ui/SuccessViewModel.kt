package br.com.fernandorubini.teste_fernando_rubini.register.success.ui

import br.com.fernandorubini.teste_fernando_rubini.base.utils.SingleLiveEvent
import br.com.fernandorubini.teste_fernando_rubini.base.viewmodel.BaseViewModel

class SuccessViewModel : BaseViewModel<SuccessViewEstate, SuccessViewAction>() {
    override val viewState = SingleLiveEvent<SuccessViewEstate>()

    override fun dispatchViewAction(viewAction: SuccessViewAction) =
        when (viewAction) {
            is SuccessViewAction.ButtonToStart -> handleButtonToStart(
                viewAction.name,
                viewAction.email,
                viewAction.cpf
            )
        }

    private fun handleButtonToStart(name: String?, email: String?, cpf: String?) {
        viewState.value =
            SuccessViewEstate.NavigateHome(name.orEmpty(), email.orEmpty(), cpf.orEmpty())
    }
}