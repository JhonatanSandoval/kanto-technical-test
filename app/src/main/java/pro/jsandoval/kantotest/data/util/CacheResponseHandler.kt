package pro.jsandoval.kantotest.data.util

abstract class CacheResponseHandler<ViewState, Data>(
    private val response: CacheResult<Data?>
) {
    suspend fun getResult(): DataState<ViewState> {
        return when (response) {
            is CacheResult.GenericError -> DataState.error(message = "Reason: ${response.errorMessage}")

            is CacheResult.Success -> {
                if (response.value == null) {
                    DataState.error(message = "Reason: Data is NULL.")
                } else {
                    handleSuccess(resultObj = response.value)
                }
            }
        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>

}