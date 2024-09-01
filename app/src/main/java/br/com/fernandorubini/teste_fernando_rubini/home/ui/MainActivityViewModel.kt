package br.com.fernandorubini.teste_fernando_rubini.home.ui

import androidx.lifecycle.viewModelScope
import br.com.fernandorubini.teste_fernando_rubini.base.utils.SingleLiveEvent
import br.com.fernandorubini.teste_fernando_rubini.base.viewmodel.BaseViewModel
import br.com.fernandorubini.teste_fernando_rubini.home.ui.usecase.GetProductUseCase
import kotlinx.coroutines.launch


class MainActivityViewModel (
    private val getProductUseCase: GetProductUseCase
) : BaseViewModel<MainActivityViewState, MainActivityViewAction>() {
    override val viewState = SingleLiveEvent<MainActivityViewState>()

    override fun dispatchViewAction(viewAction: MainActivityViewAction) =
        when (viewAction) {
            MainActivityViewAction.LoadData -> handleLoadData()
        }

    private fun handleLoadData() {
        viewState.value = MainActivityViewState.ShowLoading
        viewModelScope.launch {
            getProductUseCase()
                .onSuccess {
                    viewState.value = MainActivityViewState.HideLoading
                    viewState.value = MainActivityViewState.LoadingData(it)
                }.onError {
                    viewState.value = MainActivityViewState.HideLoading
                    viewState.value = MainActivityViewState.Error
                }
        }
    }
}