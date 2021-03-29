package pro.jsandoval.kantotest.presentation.main.profile.records

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.presentation.main.profile.ProfileViewModel

class RecordsAdapter(private val profileViewModel: ProfileViewModel) : ListAdapter<Record, RecordViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder = RecordViewHolder(parent, this, profileViewModel)

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = getItem(position) ?: return
        holder.bindRecord(record)
    }

    class DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean = oldItem.likedByMe == newItem.likedByMe
    }

}