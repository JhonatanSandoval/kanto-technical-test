package pro.jsandoval.kantotest.presentation.main.profile

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.databinding.FragmentProfileBinding
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.presentation.main.MainViewModel
import pro.jsandoval.kantotest.presentation.main.profile.records.RecordsAdapter
import pro.jsandoval.kantotest.util.base.BaseFragment
import pro.jsandoval.kantotest.util.ext.startAlphaAnimation
import kotlin.math.abs

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val recordsAdapter by lazy { RecordsAdapter() }
    private val viewModel: ProfileViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private var isTitleVisible = false

    override fun init() {
        binding.layoutContentHeader.editProfile.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment) }
        setupCollapsingToolbar()
        setupRecordsList()
    }

    private fun setupRecordsList() {
        val recyclerView = binding.rvRecords
        recyclerView.init(this)
        recordsAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                (binding.rvRecords.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(positionStart, 0)
            }
        })
        recyclerView.adapter = recordsAdapter
    }

    private fun setupCollapsingToolbar() {
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = abs(verticalOffset) / maxScroll
            checkForTitleVisibility(percentage.toFloat())
        })
        binding.tvTitle.startAlphaAnimation(visibility = View.INVISIBLE)
    }

    private fun checkForTitleVisibility(percentage: Float) {
        if (percentage >= 0.6f) {
            if (!isTitleVisible) {
                binding.tvTitle.startAlphaAnimation(visibility = View.VISIBLE)
                binding.layoutContentHeader.root.startAlphaAnimation(visibility = View.INVISIBLE)
                isTitleVisible = true
            }
        } else {
            if (isTitleVisible) {
                binding.tvTitle.startAlphaAnimation(visibility = View.INVISIBLE)
                binding.layoutContentHeader.root.startAlphaAnimation(visibility = View.VISIBLE)
                isTitleVisible = false
            }
        }
    }

    override fun initViewModel() {
        mainViewModel.userDataEvent.observe { user -> binding.user = user }
        viewModel.recordsDataEvent.observeNotNull(this::handleRecordsList)
    }

    private fun handleRecordsList(records: List<Record>) {
        recordsAdapter.submitList(records)
        if (records.isNotEmpty()) binding.rvRecords.scrollToPosition(0)
    }

    override fun onPause() {
        super.onPause()
        binding.rvRecords.changePlayingState(false)
    }

    override fun onResume() {
        super.onResume()
        binding.rvRecords.changePlayingState(true)
    }

}