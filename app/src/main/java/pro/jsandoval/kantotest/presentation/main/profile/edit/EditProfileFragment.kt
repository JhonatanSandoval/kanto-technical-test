package pro.jsandoval.kantotest.presentation.main.profile.edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.databinding.FragmentProfileEditBinding
import pro.jsandoval.kantotest.presentation.main.MainViewModel
import pro.jsandoval.kantotest.util.ImagePicker
import pro.jsandoval.kantotest.util.base.BaseFragment
import pro.jsandoval.kantotest.util.ext.areAllPermissionsGranted
import pro.jsandoval.kantotest.util.ext.getFieldText
import pro.jsandoval.kantotest.util.ext.getTempImageFile
import pro.jsandoval.kantotest.util.ext.hasCameraPermission
import pro.jsandoval.kantotest.util.ext.requestCameraPermission


@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: EditProfileViewModel by viewModels()

    private var selectedImageUri: Uri? = null

    override fun init() {
        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.save.setOnClickListener { handleSaveUserDetails() }
        binding.changePhoto.setOnClickListener { handlePickImage() }
    }

    private fun handlePickImage() {
        if (requireContext().hasCameraPermission()) {
            startActivityForResult(ImagePicker.createPickImageChooser(requireContext()), SELECT_PHOTO)
        } else requireActivity().requestCameraPermission(CAMERA_PERMISSION)
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

        viewModel.updateUserDetails(name, username, biography, selectedImageUri)
    }

    override fun initViewModel() {
        mainViewModel.userDataEvent.observe { user -> binding.user = user }
        viewModel.userDetailsSavedDataEvent.observeNotNull { isSaved -> if (isSaved) findNavController().navigateUp() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Activity.RESULT_OK == resultCode) {
            when (requestCode) {
                SELECT_PHOTO -> {
                    data?.data?.let { imageUri ->
                        // from gallery
                        selectedImageUri = imageUri
                        binding.avatar.load(imageUri) { transformations(CircleCropTransformation()) }

                    } ?: run {
                        // from camera
                        Uri.fromFile(requireContext().getTempImageFile())?.let { imageUri ->
                            selectedImageUri = imageUri
                            binding.avatar.load(imageUri) { transformations(CircleCropTransformation()) }
                        }
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_PERMISSION -> {
                if (grantResults.areAllPermissionsGranted()) {
                    handlePickImage()
                }
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION = 0
        private const val SELECT_PHOTO = 1
    }

}