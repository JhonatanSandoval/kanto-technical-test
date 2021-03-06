package pro.jsandoval.kantotest.util.ui

import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.material.imageview.ShapeableImageView
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.domain.model.User
import pro.jsandoval.kantotest.util.ext.isNougatOrHigher

@BindingAdapter("app:loadUserAvatarSimple")
fun loadUserAvatarSimple(imageView: ShapeableImageView, user: User?) {
    user?.let {
        imageView.load(user.img) { transformations(CircleCropTransformation()) }
    }
}

@BindingAdapter("app:loadUserMiniAvatar")
fun loadUserMiniAvatar(imageView: ShapeableImageView, user: User?) {
    user?.let {
        imageView.load(user.img)
        imageView.strokeColor = ContextCompat.getColorStateList(imageView.context, R.color.mini_avatar_inner_border)
        imageView.strokeWidth = 4f
    }
}

@BindingAdapter("app:loadRecordUserSongDetails")
fun loadRecordUserSongDetails(textView: TextView, record: Record?) {
    record?.let {
        val user = record.user?.name ?: "--"
        val songName = record.song
        val text = textView.context.getString(R.string.user_sang_x, user, songName)

        val spanned = if (isNougatOrHigher()) Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT) else Html.fromHtml(text)
        textView.text = spanned
    }
}