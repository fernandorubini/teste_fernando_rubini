package br.com.fernandorubini.teste_fernando_rubini.base.network.remote

import androidx.annotation.Keep
import br.com.fernandorubini.teste_fernando_rubini.base.network.domain.model.ErrorModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@Keep
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): DigioResult<T, ErrorResponse> {
    return withContext(dispatcher) {
        try {
            DigioResult.Success(apiCall())
        } catch (exception: Exception) {
            val errorResponseException = exception as? ErrorResponse
            DigioResult.Error(errorResponseException ?: getExceptionError(exception))
        }
    }
}

private fun getExceptionError(exception: java.lang.Exception): ErrorResponse {
    return ErrorResponse(
        code = getExceptionErrorCode(exception),
        message = "Algo deu errado! Não foi possível atender sua solicitação neste momento. Por favor, tente novamente."
    )
}

private fun getExceptionErrorCode(exception: java.lang.Exception) =
    if (exception is HttpException) {
        exception.code().toString()
    } else {
        UNEXPECTED
    }

fun ErrorResponse.toErrorModel() = ErrorModel(
    errorCode = this.code.orEmpty(),
    message = this.message.orEmpty(),
    detailedMessage = this.detailedMessage.orEmpty()
)