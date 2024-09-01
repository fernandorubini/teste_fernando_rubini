package br.com.fernandorubini.teste_fernando_rubini.register.starter.ui

sealed class StarterViewState {
    data object NavigateStater : StarterViewState()
    data object NavigateHome : StarterViewState()
}