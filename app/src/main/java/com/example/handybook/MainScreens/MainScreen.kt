package com.example.handybook.MainScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.handybook.R
import com.example.handybook.databinding.FragmentMainScreenBinding
import com.google.android.material.navigation.NavigationView

class MainScreen : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        drawerLayout = binding.drawerLayout
        var navigationView = binding.navView

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        navigationView.setNavigationItemSelectedListener(this)

        var toggle = ActionBarDrawerToggle(
            requireActivity(), binding.drawerLayout, binding.toolbar, R.string.open_nav,
            R.string.close_nav
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeScreen()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        loadFragment(HomeScreen())
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeScreen())
                    binding.tvToolbar.text="Bosh Sahifa"
                    true
                }

//                R.id.search -> {
//                    loadFragment(Search())
//                    true
//                }
//
//                R.id.feather -> {
//                    loadFragment(Feather())
//                    true
//                }
//
//                R.id.saved -> {
//                    loadFragment(Saved())
//                    true
//                }
//
//                R.id.settings -> {
//                    loadFragment(Settings())
//                    true
//                }
//
                else -> {
                    loadFragment(HomeScreen())
                    binding.tvToolbar.text="Bosh Sahifa"
                    true
                }
            }
        }

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeScreen()).commit()

//            R.id.nav_settings -> requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, Settings()).commit()
//
//            R.id.nav_saved -> requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, Saved()).commit()
//
//            R.id.nav_feather -> requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, Feather()).commit()
//
//            R.id.nav_search -> requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, Search()).commit()
//
//            R.id.nav_logout -> Toast.makeText(requireContext(), "Logout!", Toast.LENGTH_SHORT)
//                .show()
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
    }

