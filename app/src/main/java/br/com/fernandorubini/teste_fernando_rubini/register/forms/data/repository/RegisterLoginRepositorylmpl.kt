package br.com.fernandorubini.teste_fernando_rubini.register.forms.data.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.safeApiCall
import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request.RegisterLoginRequest
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository.RegisterLoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class RegisterLoginRepositorylmpl : RegisterLoginRepository {
    override suspend fun createUser(registerLoginRequest: RegisterLoginRequest): DigioResult<String?, ErrorResponse> {
        return safeApiCall {
            createWithFirebase(registerLoginRequest)
        }
    }

    @Throws
    private suspend fun createWithFirebase(registerLoginRequest: RegisterLoginRequest): String? {
        val firebaseAuth = FirebaseAuth.getInstance()
        try {
            firebaseAuth.createUserWithEmailAndPassword(
                registerLoginRequest.email,
                registerLoginRequest.password
            ).await()
            return if (firebaseAuth.currentUser != null) {
                firebaseAuth.currentUser?.uid
            } else {
                null
            }
        } catch (exception: Exception) {
            throw exception
        }
    }
}