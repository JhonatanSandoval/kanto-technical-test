package pro.jsandoval.kantotest.data.repository

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import pro.jsandoval.kantotest.data.local.room.KantoTestDatabase
import pro.jsandoval.kantotest.data.local.room.dao.UserDao
import pro.jsandoval.kantotest.data.mapper.toEntity
import pro.jsandoval.kantotest.data.mapper.toModel
import pro.jsandoval.kantotest.data.remote.Api
import pro.jsandoval.kantotest.data.remote.UserDetailsResponse
import pro.jsandoval.kantotest.data.util.ApiResponseHandler
import pro.jsandoval.kantotest.data.util.DataState
import pro.jsandoval.kantotest.data.util.safeApiCall
import pro.jsandoval.kantotest.domain.model.User
import pro.jsandoval.kantotest.domain.model.User.Companion.user
import pro.jsandoval.kantotest.domain.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: Api,
    private val database: KantoTestDatabase
) : UserRepository {

    private val userDao: UserDao by lazy { database.userDao() }

    override suspend fun fetchUserDetails(): Flow<DataState<Boolean>> = flow {
        emit(DataState.loading(true))
        val apiCall = safeApiCall { api.getUserDetails() }
        emit(
            object : ApiResponseHandler<Boolean, UserDetailsResponse>(response = apiCall) {
                override suspend fun handleSuccess(resultObj: UserDetailsResponse): DataState<Boolean> {
                    userDao.insert(resultObj.toEntity())
                    return DataState.data(data = true)
                }
            }.getResult()
        )
    }

    override suspend fun get(): Flow<User> = flow {
        userDao.get().collect { entity ->
            entity?.let { emit(it.toModel()) }
        }
    }

    override suspend fun updateCurrentUser(userDetails: User) {
        userDao.getSimple()?.let { entity ->
            entity.name = userDetails.name
            entity.username = "@${userDetails.username}"
            entity.biography = userDetails.biography
            userDao.update(entity)
        }
    }

    private suspend fun updateUserAvatar(img: String) {
        userDao.getSimple()?.let { entity ->
            entity.img = img
            userDao.update(entity)
        }
    }

    override suspend fun uploadUserAvatar(userId: String, filePath: Uri) {
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("avatars/${userId}")
        try {
            storageReference.putFile(filePath).await()
            val innerTask = storageReference.downloadUrl.await()
            innerTask?.toString()?.let { imageUrl ->
                updateUserAvatar(imageUrl)
            }
        } catch (e: FirebaseException) {
            Timber.e(e, "there was an error uploading the new avatar")
        }
    }

    /**
     * Before api integration:
     */
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