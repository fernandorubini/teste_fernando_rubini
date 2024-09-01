package br.com.fernandorubini.teste_fernando_rubini.register.forms.di

import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.repository.RegisterLoginRepositorylmpl
import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.repository.RegisterUserDataRepositorylmpl
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository.RegisterLoginRepository
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository.RegisterUserDataRepository
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase.RegisterLoginUseCase
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase.RegisterLoginUseCaselmpl
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase.RegisterUserDataUseCase
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase.RegisterUserDataUseCaselmpl
import br.com.fernandorubini.teste_fernando_rubini.register.forms.ui.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel { RegisterViewModel(get(), get()) }
    factory<RegisterLoginRepository> { RegisterLoginRepositorylmpl() }
    factory<RegisterUserDataRepository> { RegisterUserDataRepositorylmpl() }
    factory<RegisterLoginUseCase> { RegisterLoginUseCaselmpl(get()) }
    factory<RegisterUserDataUseCase> { RegisterUserDataUseCaselmpl(get()) }
}