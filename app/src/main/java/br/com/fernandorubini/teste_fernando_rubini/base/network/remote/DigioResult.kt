package br.com.fernandorubini.teste_fernando_rubini.base.network.remote

import androidx.annotation.Keep

@Keep
sealed class DigioResult<out D, out E> {
    data class Success<D>(val value: D) : DigioResult<D, Nothing>()
    data class Error<E>(val value: E) : DigioResult<Nothing, E>()

    inline fun handleResult(
        onSuccess: (result: D) -> Unit = {},
        onError: (error: E) -> Unit = {},
        onFinish: (D?) -> Unit = {}
    ): D? = when (this) {
        is Success -> {
            onSuccess(value)
            onFinish(value)
            value
        }

        is Error -> {
            onError(value)
            onFinish(null)
            null
        }
    }

    inline fun <T> mapSuccess(transform: (D) -> T): DigioResult<T, E> = when (this) {
        is Success -> Success(transform(value))
        is Error -> Error(value)
    }

    inline fun getSuccess(): D? = when (this) {
        is Success -> value
        is Error -> null
    }

    inline fun getError(): E? = when (this) {
        is Success -> null
        is Error -> value
    }

    inline fun <T> mapError(transform: (E) -> T): DigioResult<D, T> = when (this) {
        is Success -> Success(value)
        is Error -> Error(transform(value))
    }

    inline fun onSuccess(block: (D) -> Unit): DigioResult<D, E> {
        if (this is Success) block(value)
        return this
    }

    inline fun onError(block: (E) -> Unit): DigioResult<D, E> {
        if (this is Error) block(value)
        return this
    }

    inline fun onFinish(block: (D?) -> Unit): DigioResult<D, E> {
        if (this is Success) block(value) else block(null)
        return this
    }

    inline fun <T, F> flatMap(
        transformSuccess: (D) -> DigioResult<T, F>,
        transformError: (E) -> DigioResult<T, F>
    ): DigioResult<T, F> = when (this) {
        is Success -> transformSuccess(value)
        is Error -> transformError(value)
    }

    suspend fun <T, F> then(
        result: suspend (DigioResult<D, E>) -> DigioResult<T, F>
    ): DigioResult<T, F> = when (this) {
        is Success -> result(this)
        is Error -> Error(value as F)
    }

    fun isSuccess() = (this is Success)
}
