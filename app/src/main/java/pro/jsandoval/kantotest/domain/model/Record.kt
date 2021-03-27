package pro.jsandoval.kantotest.domain.model

data class Record(
    var user: User? = null,
    var song: String = "",
    var video: String = "",
    var preview: String = "",
    var likes: Int = 0
) {
    companion object {
        fun record(block: Record.() -> Unit): Record = Record().apply(block)
    }
}