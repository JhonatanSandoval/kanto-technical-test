package pro.jsandoval.kantotest.util.ui

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<out T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView)