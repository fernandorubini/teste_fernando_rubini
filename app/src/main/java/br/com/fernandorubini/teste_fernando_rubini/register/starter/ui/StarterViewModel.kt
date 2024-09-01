package br.com.fernandorubini.teste_fernando_rubini.register.starter.ui

import br.com.fernandorubini.teste_fernando_rubini.base.utils.SingleLiveEvent
import br.com.fernandorubini.teste_fernando_rubini.base.viewmodel.BaseViewModel

class StarterViewModel : BaseViewModel<StarterViewState, StarterViewAction>() {
    override val viewState = SingleLiveEvent<StarterViewState>()

    override fun dispatchViewAction(viewAction: StarterViewAction) =
        when (viewAction) {
            is StarterViewAction.ButtonStarter -> handleButtonStarter()
            is StarterViewAction.ButtonAlready -> handleButtonAlready()
        }

    private fun handleButtonAlready() {
        viewState.value = StarterViewState.NavigateHome
    }

    private fun handleButtonStarter() {
        viewState.value = StarterViewState.NavigateStater
    }
}