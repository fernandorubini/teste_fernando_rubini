package br.com.fernandorubini.teste_fernando_rubini.home.domain.model

import androidx.annotation.Keep

@Keep
data class DigioProductModel(
    val cashModel: CashModel,
    val productsModel: List<ProductModel>,
    val spotlightsModel: List<SpotlightModel>
)
