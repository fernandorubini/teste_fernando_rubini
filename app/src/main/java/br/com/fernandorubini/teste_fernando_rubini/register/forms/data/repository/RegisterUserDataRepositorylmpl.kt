package br.com.fernandorubini.teste_fernando_rubini.register.forms.data.repository

import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.DigioResult
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.ErrorResponse
import br.com.fernandorubini.teste_fernando_rubini.base.network.remote.safeApiCall
import br.com.fernandorubini.teste_fernando_rubini.register.forms.data.request.RegisterUserDataRequest
import br.com.fernandorubini.teste_fernando_rubini.register.forms.domain.repository.RegisterUserDataRepository
import br.com.fernandorubini.teste_fernando_rubini.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RegisterUserDataRepositorylmpl : RegisterUserDataRepository {
    override suspend fun registerUserData(
        registerUserDataRequest: RegisterUserDataRequest,
        id: String
    ): DigioResult<Unit, ErrorResponse> {
        return safeApiCall {
            saveFireStore(registerUserDataRequest, id)
        }
    }

    private suspend fun saveFireStore(
        registerUserDataRequest: RegisterUserDataRequest,
        id: String
    ) {
        val firebaseFirestore = FirebaseFirestore.getInstance()
        try {
            firebaseFirestore.collection(Constants.USER)
                .document(id)
                .set(registerUserDataRequest)
                .await()
        } catch (exception: Exception) {
            throw exception
        }
    }
}