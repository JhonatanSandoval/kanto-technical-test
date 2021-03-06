package pro.jsandoval.kantotest.data.util

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class GenericError(val code: Int? = null, val message: String? = null) : ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()
}