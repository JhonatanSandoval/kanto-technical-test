package pro.jsandoval.kantotest.presentation.main.profile.records

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pro.jsandoval.kantotest.domain.model.Record

class RecordsAdapter : ListAdapter<Record, RecordViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder = RecordViewHolder(parent)

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = getItem(position) ?: return
        holder.binding.record = record
        holder.bindRecord(record)
    }

    class DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean =
            oldItem.user == newItem.user
                    && oldItem.song == newItem.song
                    && oldItem.likes == newItem.likes
                    && oldItem.preview == newItem.preview
    }

}