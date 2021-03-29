package pro.jsandoval.kantotest.data.mapper

import pro.jsandoval.kantotest.data.local.room.entity.UserEntity
import pro.jsandoval.kantotest.data.local.room.entity.UserEntity.Companion.userEntity
import pro.jsandoval.kantotest.data.remote.UserDetailsResponse
import pro.jsandoval.kantotest.domain.model.User
import pro.jsandoval.kantotest.domain.model.User.Companion.user
import timber.log.Timber
import java.lang.Exception
import java.lang.IllegalArgumentException

fun UserDetailsResponse.toEntity(): UserEntity {
    try {
        return userEntity {
            val response = this@toEntity
            userId = response.userIdEncrypted ?: ""
            name = response.name ?: ""
            username = response.username ?: ""
            img = response.profilePicture ?: ""
            biography = response.biography ?: ""
            followers = response.followers ?: 0
            followed = response.followed ?: 0
            views = response.views ?: 0
        }
    } catch (ex: Exception) {
        Timber.e(ex)
        throw IllegalArgumentException("exception in toEntity()")
    }
}

fun UserEntity.toModel(): User {
    try {
        return user {
            val entity = this@toModel
            userId = entity.userId
            name = entity.name
            username = entity.username
            img = entity.img
            biography = entity.biography
            followers = entity.followers
            followed = entity.followed
            views = entity.views
        }
    } catch (ex: Exception) {
        Timber.e(ex)
        throw IllegalArgumentException("exception in toModel()")
    }
}