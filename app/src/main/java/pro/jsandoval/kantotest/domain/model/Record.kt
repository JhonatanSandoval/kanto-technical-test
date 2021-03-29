package pro.jsandoval.kantotest.domain.model

data class Record(
    var id: Long = 0,
    var user: User? = null,
    var song: String = "",
    var video: String = "",
    var preview: String = "",
    var likes: Int = 0,
    var likedByMe: Boolean = false
) {
    companion object {
        fun record(block: Record.() -> Unit): Record = Record().apply(block)
    }
}