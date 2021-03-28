package pro.jsandoval.kantotest.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<NetworkObj, CacheObj, ViewState>
constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val apiCall: suspend () -> NetworkObj?,
    private val cacheCall: suspend () -> CacheObj?
) {

    val result: Flow<DataState<ViewState>> = flow {
        emit(buildLoading(true))
        emit(returnCache())
        when (val apiResult = safeApiCall(dispatcher) { apiCall.invoke() }) {
            is ApiResult.GenericError -> {
                emit(
                    buildError<ViewState>(
                        apiResult.message ?: ErrorHandling.ERROR_UNKNOWN
                    )
                )
            }

            is ApiResult.NetworkError -> {
                emit(buildError<ViewState>(ErrorHandling.NETWORK_ERROR))
            }

            is ApiResult.Success<*> -> {
                if (apiResult.value == null) {
                    emit(buildError<ViewState>(ErrorHandling.ERROR_UNKNOWN))
                } else {
                    updateCache(apiResult.value as NetworkObj)
                }
            }
        }

        emit(returnCache())
    }

    private suspend fun returnCache(): DataState<ViewState> {
        val cacheResult = safeCacheCall(dispatcher) { cacheCall.invoke() }
        return object : CacheResponseHandler<ViewState, CacheObj>(
            response = cacheResult
        ) {
            override suspend fun handleSuccess(resultObj: CacheObj): DataState<ViewState> {
                return handleCacheSuccess(resultObj)
            }
        }.getResult()
    }

    abstract suspend fun updateCache(networkObject: NetworkObj)
    abstract fun handleCacheSuccess(resultObj: CacheObj): DataState<ViewState> // make sure to return null for stateEvent

}

fun <ViewState> buildLoading(isLoading: Boolean): DataState<ViewState> {
    return DataState.loading(isLoading)
}

fun <ViewState> buildError(message: String): DataState<ViewState> {
    return DataState.error(message = "Reason: $message")
}