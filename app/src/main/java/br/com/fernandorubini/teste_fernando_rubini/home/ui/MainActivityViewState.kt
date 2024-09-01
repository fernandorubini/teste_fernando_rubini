package br.com.fernandorubini.teste_fernando_rubini.home.ui

import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.DigioProductModel


sealed class MainActivityViewState {
    data class LoadingData(val digioProductModel: DigioProductModel): MainActivityViewState()
    data object Error: MainActivityViewState()
    data object ShowLoading: MainActivityViewState()
    data object HideLoading: MainActivityViewState()
}