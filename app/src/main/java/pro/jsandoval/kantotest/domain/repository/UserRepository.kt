package pro.jsandoval.kantotest.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.domain.model.User

interface UserRepository {

    suspend fun get(): Flow<User?>

}