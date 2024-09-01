package br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request.RegisterLoginRequest
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository.RegisterLoginRepository

interface RegisterLoginUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): DigioResult<String, ErrorResponse>
}

class RegisterLoginUseCaselmpl(
    private val repositoryLogin: RegisterLoginRepository,
) : RegisterLoginUseCase {
    override suspend fun invoke(
        email: String,
        password: String
    ): DigioResult<String, ErrorResponse> {
        return repositoryLogin.createUser(
            RegisterLoginRequest(
                email = email,
                password = password
            )
        ).mapSuccess {
            it.orEmpty()
        }
    }
}