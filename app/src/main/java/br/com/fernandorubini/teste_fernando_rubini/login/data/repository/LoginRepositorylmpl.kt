package br.com.fernandorubini.teste_fernando_rubini.login.data.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.response.ResponseGeneric
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.safeApiCall
import br.com.fernandorubini.teste_fernando_rubini.login.data.response.LoginRequest
import br.com.fernandorubini.teste_fernando_rubini.login.data.response.LoginResponse
import br.com.fernandorubini.teste_fernando_rubini.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): DigioResult<ResponseGeneric<LoginResponse>?, ErrorResponse> {
        return safeApiCall {
            loginWithFirebase(loginRequest)
        }
    }

    @Throws
    private suspend fun loginWithFirebase(loginRequest: LoginRequest): ResponseGeneric<LoginResponse>? {
        val firebaseAuth = FirebaseAuth.getInstance()
        try {
            firebaseAuth.signInWithEmailAndPassword(
                loginRequest.email,
                loginRequest.password
            ).await()
            return if (firebaseAuth.currentUser != null) {
                ResponseGeneric(data = LoginResponse(user = firebaseAuth.currentUser?.email))
            } else {
                null
            }
        } catch (exception: Exception) {
            throw exception
        }
    }
}