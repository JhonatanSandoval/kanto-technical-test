package pro.jsandoval.kantotest.presentation.main

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.databinding.ActivityMainBinding
import pro.jsandoval.kantotest.util.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val navController: NavController by lazy { findNavController(R.id.fragmentContainer) }

    override fun init() {
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.bottomNavView.apply {
            setupWithNavController(navController)
            setOnNavigationItemReselectedListener {
                // do nothing, or something
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val shouldShowBottomMenu = when (destination.id) {
                R.id.opt_profile_edit -> false
                else -> true
            }
            binding.bottomNavView.isVisible = shouldShowBottomMenu
            binding.fabSign.isVisible = shouldShowBottomMenu
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun initViewModel() {
        // do something, or nothing
        viewModel.loadCurrentUser()
    }

}