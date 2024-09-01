package br.com.fernandorubini.teste_fernando_rubini.login.di

import br.com.fernandorubini.teste_fernando_rubini.login.data.repository.LoginRepositoryImpl
import br.com.fernandorubini.teste_fernando_rubini.login.domain.repository.LoginRepository
import br.com.fernandorubini.teste_fernando_rubini.login.domain.usecase.LoginUseCase
import br.com.fernandorubini.teste_fernando_rubini.login.domain.usecase.LoginUseCaseImpl
import br.com.fernandorubini.teste_fernando_rubini.login.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get()) }
    factory<LoginRepository> { LoginRepositoryImpl() }
    factory<LoginUseCase> { LoginUseCaseImpl(get()) }
}