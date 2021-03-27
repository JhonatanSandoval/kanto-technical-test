package pro.jsandoval.kantotest.data

data class DataState<T>(
    var data: T? = null,
    var loading: Boolean = false,
    var type: MessageType = MessageType.Success,
    var message: String? = null
) {
    companion object {
        fun <T> error(message: String?): DataState<T> {
            return DataState(type = MessageType.Error, message = message)
        }

        fun <T> loading(isLoading: Boolean): DataState<T> {
            return DataState(message = null, loading = isLoading, data = null)
        }

        fun <T> data(message: String? = null, data: T? = null): DataState<T> {
            return DataState(
                message = null, type = MessageType.Success,
                data = data, loading = false
            )
        }
    }
}

sealed class MessageType {
    object Success : MessageType()
    object Error : MessageType()
}