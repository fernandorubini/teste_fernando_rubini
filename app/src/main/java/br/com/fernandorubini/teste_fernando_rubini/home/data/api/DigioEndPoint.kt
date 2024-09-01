package br.com.fernandorubini.teste_fernando_rubini.home.data.api

import br.com.fernandorubini.teste_fernando_rubini.home.data.response.DigitProductsResponse
import retrofit2.http.GET

interface DigioEndPoint {
    @GET("sandbox/products")
    suspend fun getProducts(): DigitProductsResponse
}