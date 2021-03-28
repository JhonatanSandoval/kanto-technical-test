package pro.jsandoval.kantotest.data.util

import pro.jsandoval.kantotest.data.util.ErrorHandling.NETWORK_ERROR

abstract class ApiResponseHandler<ViewState, Data>(
    private val response: ApiResult<Data?>
) {
    suspend fun getResult(): DataState<ViewState> {
        return when (response) {
            is ApiResult.GenericError ->
                DataState.error(
                    message = response.message.toString()
                )

            is ApiResult.NetworkError -> DataState.error(message = NETWORK_ERROR)

            is ApiResult.Success ->
                if (response.value == null) {
                    DataState.error(message = "Data is NULL.")
                } else {
                    handleSuccess(resultObj = response.value)
                }

        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>
}