package br.com.fernandorubini.teste_fernando_rubini.home.di

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.client.ApiClient
import br.com.fernandorubini.teste_fernando_rubini.home.domain.usecase.GetProductUseCaseImpl
import br.com.fernandorubini.teste_fernando_rubini.home.data.api.DigioEndPoint
import br.com.fernandorubini.teste_fernando_rubini.home.data.mapper.ProductMapperImpl
import br.com.fernandorubini.teste_fernando_rubini.home.data.repository.ProductRepositoryImpl
import br.com.fernandorubini.teste_fernando_rubini.home.domain.mapper.ProductMapper
import br.com.fernandorubini.teste_fernando_rubini.home.domain.repository.ProductRepository
import br.com.fernandorubini.teste_fernando_rubini.home.ui.MainActivityViewModel
import br.com.fernandorubini.teste_fernando_rubini.home.ui.usecase.GetProductUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainActivityViewModel(get()) }
    factory { ApiClient.create(DigioEndPoint::class.java) }
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    factory<ProductMapper> { ProductMapperImpl() }
    factory<GetProductUseCase> { GetProductUseCaseImpl(get(), get()) }
}