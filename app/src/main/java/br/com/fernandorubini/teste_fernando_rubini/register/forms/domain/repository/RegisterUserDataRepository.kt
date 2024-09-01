package br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request.RegisterUserDataRequest

interface RegisterUserDataRepository {
    suspend fun registerUserData(
        registerUserDataRequest: RegisterUserDataRequest,
        id: String
    ): DigioResult<Unit, ErrorResponse>
}