package br.com.fernandorubini.teste_fernando_rubini.register.starter.ui

sealed class StarterViewAction {
    data object ButtonStarter : StarterViewAction()
    data object ButtonAlready : StarterViewAction()
}