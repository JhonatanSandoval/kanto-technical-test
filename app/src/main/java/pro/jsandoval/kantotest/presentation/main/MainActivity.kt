package pro.jsandoval.kantotest.presentation.main

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
            binding.bottomNavView.isVisible = when (destination.id) {
                R.id.opt_profile_edit -> false
                else -> true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun initViewModel() {
        // do something, or nothing
    }

}