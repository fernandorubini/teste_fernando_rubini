package br.com.fernandorubini.teste_fernando_rubini.home.domain.usecase

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.home.domain.mapper.ProductMapper
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.DigioProductModel
import br.com.fernandorubini.teste_fernando_rubini.home.domain.repository.ProductRepository
import br.com.fernandorubini.teste_fernando_rubini.home.ui.usecase.GetProductUseCase

class GetProductUseCaseImpl(
    private val repository: ProductRepository,
    private val mapper: ProductMapper
) : GetProductUseCase {
    override suspend fun invoke(): DigioResult<DigioProductModel, ErrorResponse> {
        return repository.getProduct()
            .mapSuccess {
                mapper.mapFrom(it)
            }
            .mapError { it }
    }
}