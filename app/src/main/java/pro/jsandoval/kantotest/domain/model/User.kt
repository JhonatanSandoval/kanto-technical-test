package pro.jsandoval.kantotest.domain.model

data class User(
    var userId: String = "",
    var name: String = "",
    var username: String = "",
    var img: String = "",
    var biography: String = "",
    var followers: Int = 0,
    var followed: Int = 0,
    var views: Int = 0
) {
    companion object {
        fun user(block: User.() -> Unit): User = User().apply(block)
    }
}