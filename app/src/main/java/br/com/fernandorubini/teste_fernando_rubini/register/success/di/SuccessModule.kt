package br.com.fernandorubini.teste_fernando_rubini.register.success.di

import br.com.fernandorubini.teste_fernando_rubini.register.success.ui.SuccessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val successModule = module {
    viewModel { SuccessViewModel() }
}