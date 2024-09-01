package br.com.fernandorubini.teste_fernando_rubini.home.data.mapper

import br.com.fernandorubini.teste_fernando_rubini.home.data.response.DigitProductsResponse
import br.com.fernandorubini.teste_fernando_rubini.home.domain.mapper.ProductMapper
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.CashModel
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.DigioProductModel
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.ProductModel
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.SpotlightModel

class ProductMapperImpl : ProductMapper {
    override fun mapFrom(from: DigitProductsResponse): DigioProductModel {
        val cashModel = CashModel(
            bannerURL = from.cashModel?.bannerURL.orEmpty(),
            title = from.cashModel?.title.orEmpty()
        )
        val productsModel = from.products?.map {
            ProductModel(
                imageURL = it.imageURL.orEmpty(),
                name = it.name.orEmpty(),
                description = it.description.orEmpty()
            )
        }
        val spotlightsModel = from.spotlight?.map {
            SpotlightModel(
                bannerURL = it.bannerURL.orEmpty(),
                name = it.name.orEmpty(),
                description = it.description.orEmpty()
            )
        }
        return DigioProductModel(
            cashModel = cashModel,
            productsModel = productsModel.orEmpty(),
            spotlightsModel = spotlightsModel.orEmpty()
        )
    }
}