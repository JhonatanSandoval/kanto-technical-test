package pro.jsandoval.kantotest.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pro.jsandoval.kantotest.domain.model.User
import pro.jsandoval.kantotest.domain.model.User.Companion.user
import pro.jsandoval.kantotest.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(

) : UserRepository {

    override suspend fun get(): Flow<User?> = flow {
        emit(mock_user())
    }

    private fun mock_user(): User {
        return user {
            img = "https://ks-profiles-dev.s3.amazonaws.com/media/user_photos/2949/tempImage1578523169147.png"
            name = "TestAndroide"
            username = "@testotesto"
            biography = "Esta es mi biografia"
            followers = 20
            followed = 5
            views = 48
        }
    }
}