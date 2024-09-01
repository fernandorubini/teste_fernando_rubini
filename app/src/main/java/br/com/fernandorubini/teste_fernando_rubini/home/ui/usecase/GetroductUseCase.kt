package br.com.fernandorubini.teste_fernando_rubini.home.ui.usecase

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.DigioProductModel

interface GetProductUseCase {
    suspend operator fun invoke(): DigioResult<DigioProductModel, ErrorResponse>
}