package br.com.fernandorubini.teste_fernando_rubini.home.domain.mapper

import br.com.fernandorubini.teste_fernando_rubini.base.network.domain.Mapper
import br.com.fernandorubini.teste_fernando_rubini.home.data.response.DigitProductsResponse
import br.com.fernandorubini.teste_fernando_rubini.home.domain.model.DigioProductModel

interface ProductMapper : Mapper<DigitProductsResponse, DigioProductModel>