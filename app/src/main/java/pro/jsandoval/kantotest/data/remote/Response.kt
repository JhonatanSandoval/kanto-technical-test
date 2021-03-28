package pro.jsandoval.kantotest.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("user_id_encrypted") @Expose val userIdEncrypted: String? = null,
    @SerializedName("name") @Expose val name: String? = null,
    @SerializedName("user_name") @Expose val username: String? = null,
    @SerializedName("profilePicture") @Expose val profilePicture: String? = null,
    @SerializedName("biography") @Expose val biography: String? = null,
    @SerializedName("followers") @Expose val followers: Int? = 0,
    @SerializedName("followed") @Expose val followed: Int? = 0,
    @SerializedName("views") @Expose val views: Int? = 0
)

data class RecordResponse(
    @SerializedName("user") @Expose val user: UserDetailsResponse? = null,
    @SerializedName("songName") @Expose val songName: String? = null,
    @SerializedName("record_video") @Expose val recordVideo: String? = null,
    @SerializedName("preview_img") @Expose val previewImg: String? = null,
    @SerializedName("likes") @Expose val likes: Int? = 0
)