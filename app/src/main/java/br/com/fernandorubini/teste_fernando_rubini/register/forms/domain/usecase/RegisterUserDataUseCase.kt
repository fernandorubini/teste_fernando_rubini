package br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.usecase

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request.RegisterUserDataRequest
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.model.User
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository.RegisterUserDataRepository

interface RegisterUserDataUseCase {
    suspend operator fun invoke(
        user: User,
        id: String
    ): DigioResult<Unit, ErrorResponse>
}

class RegisterUserDataUseCaselmpl(
    private val repositoryUserData: RegisterUserDataRepository
) : RegisterUserDataUseCase {
    override suspend fun invoke(user: User, id: String): DigioResult<Unit, ErrorResponse> {
        return repositoryUserData.registerUserData(
            RegisterUserDataRequest(
                name = user.name,
                cpf = user.cpf,
                phoneNumber = user.phoneNumber,
                email = user.email,
                picture = user.picture
            ), id
        ).mapSuccess {
            it
        }
    }
}