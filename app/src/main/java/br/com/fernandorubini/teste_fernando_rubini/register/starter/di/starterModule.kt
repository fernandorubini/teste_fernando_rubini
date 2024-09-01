package br.com.fernandorubini.teste_fernando_rubini.register.starter.di

import br.com.fernandorubini.teste_fernando_rubini.register.starter.ui.StarterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val starterModule = module {
    viewModel { StarterViewModel() }
}