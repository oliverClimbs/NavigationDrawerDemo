package com.oliverclimbs.navigationdrawertest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oliverclimbs.navigationdrawertest.R
import com.oliverclimbs.navigationdrawertest.R.id.action_settings
import com.oliverclimbs.navigationdrawertest.databinding.FragmentHomeBinding


class HomeFragment : Fragment()
{

  private var _binding: FragmentHomeBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private var settingsMenuItem: MenuItem? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
                           ): View
  {
    val homeViewModel =
      ViewModelProvider(this)[HomeViewModel::class.java]

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textHome
    homeViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?)
  {
    setupMenu()
    super.onViewCreated(view, savedInstanceState)

  }

  private fun setupMenu()
  {
    requireActivity().addMenuProvider(
      object : MenuProvider
      {
        override fun onPrepareMenu(menu: Menu)
        {
          settingsMenuItem = menu.findItem(action_settings)
        }

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater)
        {
          menuInflater.inflate(R.menu.menu_main, menu)

        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean
        {
          when (menuItem.itemId)
          {
            action_settings ->
            {
              Log.d("TAG", "onMenuItemSelected: settings")
            }
          }
            return true
        }
      })
  }

  // ---------------------------------------------------------------------------------------------
  override fun onDestroyView()
  {
    super.onDestroyView()
    _binding = null

  }
}