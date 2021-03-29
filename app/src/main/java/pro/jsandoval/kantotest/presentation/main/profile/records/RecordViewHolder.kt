package pro.jsandoval.kantotest.presentation.main.profile.records

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.api.load
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.databinding.ItemRecordBinding
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.presentation.main.profile.ProfileViewModel
import pro.jsandoval.kantotest.util.ext.inflater
import pro.jsandoval.kantotest.util.ui.BindingViewHolder

class RecordViewHolder(
    private val parent: ViewGroup,
    private val recordsAdapter: RecordsAdapter,
    private val profileViewModel: ProfileViewModel,
    val binding: ItemRecordBinding = ItemRecordBinding.inflate(parent.inflater(), parent, false)
) : BindingViewHolder<ItemRecordBinding>(binding.root), VideoPlayerEventListener {

    private lateinit var record: Record
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    fun bindRecord(record: Record) {
        this.record = record
        binding.record = record
        binding.imagePreview.load(record.preview)
        binding.clickListener = clickListener
        checkIfLikedByMe()
    }

    private val clickListener = object : ClickListener {
        override fun likeRecord(record: Record) {
            profileViewModel.likeRecord(record)
            recordsAdapter.notifyItemChanged(adapterPosition)
        }
    }

    private fun checkIfLikedByMe() {
        val tintColor = if (record.likedByMe) R.color.liked else R.color.grayLight
        binding.likeImage.setImageResource(if (record.likedByMe) R.drawable.ic_liked else R.drawable.ic_like)
        binding.likeText.text = getLikeString()
        binding.likeText.setTextColor(ContextCompat.getColor(parent.context, tintColor))
    }

    private fun getLikeString(): String {
        return if (record.likes == 1) parent.context.getString(R.string.x_like, record.likes)
        else parent.context.getString(R.string.x_likes, record.likes)
    }

    override fun onPrePlay(player: SimpleExoPlayer) {
        simpleExoPlayer = player
        simpleExoPlayer.playVideo()
        binding.playerView.player = simpleExoPlayer
    }

    override fun onPlayCanceled() {
        binding.playerView.player = null
        handler.removeCallbacks(updatePlayerRunnable)
        hideVideo()
    }

    private fun showVideo() {
        binding.playerView.visibility = View.VISIBLE
        binding.videoProgress.max = simpleExoPlayer.duration.toInt()
    }

    private val updatePlayerRunnable = object : Runnable {
        override fun run() {
            binding.videoProgress.progress = simpleExoPlayer.currentPosition.toInt()
            handler.postDelayed(this, 500)
        }
    }

    private fun hideVideo() {
        binding.videoProgress.progress = 0
        binding.playerView.visibility = View.GONE
    }

    override fun onPlay() {
        itemView.postDelayed({
            if (binding.playerView.player != null) {
                showVideo()
                handler.postDelayed(updatePlayerRunnable, 200)
            }
        }, DELAY_BEFORE_HIDE_THUMBNAIL)
    }

    private fun SimpleExoPlayer.playVideo() {
        stop(true)
        setMediaItem(MediaItem.fromUri(record.video))
        prepare()
    }

    interface ClickListener {
        fun likeRecord(record: Record)
    }

    companion object {
        private const val DELAY_BEFORE_HIDE_THUMBNAIL = 500L
    }

}