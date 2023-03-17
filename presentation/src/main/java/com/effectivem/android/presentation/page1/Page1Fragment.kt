package com.effectivem.android.presentation.page1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.presentation.R
import com.effectivem.android.presentation.databinding.FragmentPage1Binding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class Page1Fragment : Fragment() {
    private var _binding: FragmentPage1Binding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: Page1FragmentArgs by navArgs()

    private val page1ViewModel: Page1ViewModel by viewModel { parametersOf(args.firstName) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPage1Binding.inflate(inflater, container, false)

        binding.latest.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.flashSale.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    page1ViewModel.profile.collect {profile ->
                        profile?.let {
                            updateUi(it)
                        }
                    }
                }
            }

            bottomNavigation.apply {
                selectedItemId = R.id.home

                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.profile -> {
                            findNavController().navigate(
                                Page1FragmentDirections.tabBarShowProfile(args.firstName)
                            )
                            true
                        }
                        else -> false
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    page1ViewModel.latestItem.collect { items ->
                        latest.adapter = LatestAdapter(items)
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    page1ViewModel.saleItems.collect { items ->
                        flashSale.adapter = SaleAdapter(items)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(profile: Profile) {
        binding.apply {
            profilePhoto.setImageBitmap(profile.photoFileBitmap)
        }
    }
}