package br.com.fernandorubini.teste_fernando_rubini.home.data.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.safeApiCall
import br.com.fernandorubini.teste_fernando_rubini.base.persistance.preference.DigioPreferencesCache
import br.com.fernandorubini.teste_fernando_rubini.home.data.api.DigioEndPoint
import br.com.fernandorubini.teste_fernando_rubini.home.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: DigioEndPoint,
    val cache: DigioPreferencesCache
) :
    ProductRepository {
    override suspend fun getProduct() = safeApiCall {
        api.getProducts()
    }
}