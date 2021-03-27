package pro.jsandoval.kantotest.presentation.main.profile.records

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import pro.jsandoval.kantotest.databinding.ItemRecordBinding
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.util.ext.inflater
import pro.jsandoval.kantotest.util.ui.BindingViewHolder

class RecordViewHolder(
    private val parent: ViewGroup,
    val binding: ItemRecordBinding = ItemRecordBinding.inflate(parent.inflater(), parent, false)
) : BindingViewHolder<ItemRecordBinding>(binding.root), VideoPlayerEventListener {

    private lateinit var record: Record
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    fun bindRecord(record: Record) {
        this.record = record
        binding.imagePreview.load(record.preview)
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
        binding.imagePreview.visibility = View.INVISIBLE
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
        binding.imagePreview.visibility = View.VISIBLE
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

    companion object {
        private const val DELAY_BEFORE_HIDE_THUMBNAIL = 500L
    }

}