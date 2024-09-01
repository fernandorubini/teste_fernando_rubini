package br.com.fernandorubini.teste_fernando_rubini.home.data.response

import androidx.annotation.Keep
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.CashModel

@Keep
data class DigitProductsResponse(
    val cashModel: CashModel?,
    val products: List<ProductResponse>?,
    val spotlight: List<SpotlightResponse>?
)

@Keep
data class ProductResponse(
    val imageURL: String?,
    val name: String?,
    val description: String?
)

@Keep
data class SpotlightResponse(
    val bannerURL: String?,
    val name: String?,
    val description: String?
)