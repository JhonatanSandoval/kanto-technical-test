package pro.jsandoval.kantotest.presentation.main.profile.edit

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.databinding.FragmentProfileEditBinding
import pro.jsandoval.kantotest.presentation.main.MainViewModel
import pro.jsandoval.kantotest.util.base.BaseFragment
import pro.jsandoval.kantotest.util.ext.getFieldText

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: EditProfileViewModel by viewModels()

    override fun init() {
        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.save.setOnClickListener { handleSaveUserDetails() }
    }

    private fun handleSaveUserDetails() {
        val name = binding.name.getFieldText()
        val username = binding.username.getFieldText()
        val biography = binding.biography.getFieldText()

        if (name.isEmpty() || name.length <= 2) {
            binding.name.error = getString(R.string.error_field_name)
            return
        }

        if (username.isEmpty() || username.length <= 5) {
            binding.username.error = getString(R.string.error_field_username)
            return
        }

        viewModel.updateUserDetails(name, username, biography)
    }

    override fun initViewModel() {
        mainViewModel.userDataEvent.observe { user -> binding.user = user }
        viewModel.userDetailsSavedDataEvent.observeNotNull { isSaved -> if (isSaved) findNavController().navigateUp() }
    }

}