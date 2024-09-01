package br.com.fernandorubini.teste_fernando_rubini.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T, A> : ViewModel() {
    abstract val viewState: MutableLiveData<T>
    abstract fun dispatchViewAction(viewAction: A)
}