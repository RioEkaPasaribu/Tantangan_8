package com.ifs21033.whatsapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.appcompat.widget.SearchView
import com.ifs21033.whatsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()
    }
    private fun setupView() {
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)
        loadFragment(FLAG_FRAGMENT_HOME)
    }
    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Add!")
                    true
                }
                R.id.action_settings -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Settings!")
                    true
                }
                R.id.action_logout -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Logout!")
                    true
                }
                else -> true
            }
        }
        binding.inAppBar.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    true
                }
                R.id.navigation_dashboard -> {
                    loadFragment(FLAG_FRAGMENT_DASHBOARD)
                    true
                }
                R.id.navigation_notifications -> {
                    loadFragment(FLAG_FRAGMENT_NOTIFICATION)
                    true
                }
                R.id.navigation_call -> {
                    loadFragment(FLAG_FRAGMENT_CALL)
                    true
                }
                else -> true
            }
        }
    }
    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inAppBar.inContentMain.frameContainer.id
        when (flag) {
            FLAG_FRAGMENT_HOME -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_home)
                    .setChecked(true)
                val homeFragment = ChatFragment()
                val bundle = Bundle().apply {
                    this.putString(
                        ChatFragment.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                homeFragment.arguments = bundle
                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        homeFragment,
                        ChatFragment::class.java.simpleName
                    )
                    .commit()
            }
            FLAG_FRAGMENT_DASHBOARD -> {
                val dashboardFragment = UpdateFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(UpdateFragment::class.java.simpleName)
                if (fragment !is UpdateFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            dashboardFragment,
                            UpdateFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
            FLAG_FRAGMENT_NOTIFICATION -> {
                val notificationFragment = CommunityFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(CommunityFragment::class.java.simpleName)
                if (fragment !is CommunityFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            notificationFragment,
                            CommunityFragment::class.java.simpleName
                        )
                        .commit()
                }
            }

            FLAG_FRAGMENT_CALL -> {
                val callFragment = CallFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(CallFragment::class.java.simpleName) // Ganti dengan CallFragment::class.java.simpleName
                if (fragment !is CallFragment) { // Ganti dengan CallFragment
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            callFragment,
                            CallFragment::class.java.simpleName
                        )
                        .commit()
                }
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Atur listener untuk pencarian
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Ketika pengguna menekan enter setelah memasukkan query
                // Buka ChatFragment dan sertakan query sebagai argument
                loadChatFragmentWithQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Logika yang dijalankan saat teks pada kolom pencarian berubah
                // Misalnya, melakukan pencarian real-time saat pengguna mengetik
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun loadChatFragmentWithQuery(query: String?) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId = binding.inAppBar.inContentMain.frameContainer.id

        val chatFragment = ChatFragment()
        val bundle = Bundle().apply {
            putString(ChatFragment.EXTRA_QUERY, query)
        }
        chatFragment.arguments = bundle

        fragmentManager.beginTransaction()
            .replace(fragmentContainerId, chatFragment)
            .commit()
    }

    companion object {
        const val FLAG_FRAGMENT_HOME = "fragment_home"
        const val FLAG_FRAGMENT_DASHBOARD = "fragment_dashboard"
        const val FLAG_FRAGMENT_NOTIFICATION = "fragment_notification"
        const val FLAG_FRAGMENT_CALL = "fragment_call"
    }
}
