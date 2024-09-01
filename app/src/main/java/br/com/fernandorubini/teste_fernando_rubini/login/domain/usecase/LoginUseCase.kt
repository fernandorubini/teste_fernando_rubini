package br.com.fernandorubini.teste_fernando_rubini.login.domain.usecase

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.login.data.response.LoginRequest
import br.com.fernandorubini.teste_fernando_rubini.login.domain.repository.LoginRepository

interface LoginUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): DigioResult<Unit, ErrorResponse>
}

class LoginUseCaseImpl(
    private val repository: LoginRepository
) : LoginUseCase {
    override suspend fun invoke(
        email: String,
        password: String
    ): DigioResult<Unit, ErrorResponse> {
        val request = LoginRequest(email = email, password = password)
        return repository.login(request).mapSuccess {
            it?.data
        }
    }
}