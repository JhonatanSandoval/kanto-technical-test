package pro.jsandoval.kantotest.presentation.main.profile.edit

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.launch
import pro.jsandoval.kantotest.domain.model.User.Companion.user
import pro.jsandoval.kantotest.domain.repository.UserRepository
import pro.jsandoval.kantotest.util.base.BaseViewModel
import pro.jsandoval.kantotest.util.event.SingleLiveEvent

class EditProfileViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val userDetailsSavedDataEvent = SingleLiveEvent<Boolean>()

    fun updateUserDetails(name: String, username: String, biography: String, newAvatarImageUri: Uri? = null) = launch {
        newAvatarImageUri?.let { userRepository.uploadUserAvatar(filePath = newAvatarImageUri) }
        userRepository.updateCurrentUser(user {
            this.name = name
            this.username = username
            this.biography = biography
        })
        userDetailsSavedDataEvent.postValue(true)
    }

}