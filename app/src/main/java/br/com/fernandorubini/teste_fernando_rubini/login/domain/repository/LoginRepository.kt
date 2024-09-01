package br.com.fernandorubini.teste_fernando_rubini.login.domain.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.response.ResponseGeneric
import br.com.fernandorubini.teste_fernando_rubini.login.data.response.LoginRequest
import br.com.fernandorubini.teste_fernando_rubini.login.data.response.LoginResponse

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): DigioResult<ResponseGeneric<LoginResponse>?, ErrorResponse>
}