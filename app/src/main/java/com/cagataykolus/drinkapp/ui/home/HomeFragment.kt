package com.cagataykolus.drinkapp.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.cagataykolus.drinkapp.R
import com.cagataykolus.drinkapp.databinding.FragmentHomeBinding
import com.cagataykolus.drinkapp.model.Drink
import com.cagataykolus.drinkapp.model.State
import coil.load
import com.cagataykolus.drinkapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val viewModel by viewModels<HomeViewModel>()

    override fun onResume() {
        super.onResume()
        getDrink()
    }

    override fun onStart() {
        super.onStart()

        observeDrink()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
        initView()
    }

    private fun setTitle() {
        requireActivity().title = getString(R.string.app_name)
    }

    /**
     * Observe data.
     */
    private fun observeDrink() {
        viewModel.drinkLiveData.observe(viewLifecycleOwner) { state ->
            hideKeyboard()
            when (state) {
                is State.Loading -> {
                    showLoading(true)
                }
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        // IMPORTANT
                        // We're getting first item because random method gives only one item.
                        Log.d("cgty",""+state.data[0].name + " " + state.data[0].image_url)
                        binding.textviewHomeName.text = state.data[0].name
                        binding.textviewHomeTagline.text = state.data[0].tagline
                        binding.imageviewHomeImage.load(state.data[0].image_url) {
                            placeholder(R.drawable.ic_bottle_vector)
                            error(R.drawable.ic_bottle_vector)
                            fallback(R.drawable.ic_bottle_vector)
                        }
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun getDrink() = viewModel.getDrink()

    /**
     * Initialize recyclerview with values.
     */
    private fun initView() {
        binding.run {
            swiperefreshlayoutHomeRefresh.setOnRefreshListener { getDrink() }

        }
        viewModel.drinkLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getDrink()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutHomeRefresh.isRefreshing = isLoading
    }

    private fun onItemClicked(drink: Drink) {
        Toast.makeText(requireContext(), drink.name, Toast.LENGTH_SHORT).show()
    }

    /**
     * Observes network changes.
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_fail)
                binding.linearlayoutHomeNetworkStatus.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_fail
                        )
                    )
                }
            } else {
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_success)
                binding.linearlayoutHomeNetworkStatus.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_success
                        )
                    )

                    animate()
                        .alpha(1f)
                        .setStartDelay(1000L)
                        .setDuration(1000L)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }
}