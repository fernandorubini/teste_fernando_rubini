package br.com.fernandorubini.teste_fernando_rubini.home.domain.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.home.data.response.DigitProductsResponse

interface ProductRepository {
    suspend fun getProduct(): DigioResult<DigitProductsResponse, ErrorResponse>
}