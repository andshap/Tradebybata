package com.effectivem.android.presentation.profile

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.presentation.R
import com.effectivem.android.presentation.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: ProfileFragmentArgs by navArgs()

    private val profileViewModel: ProfileViewModel by viewModel { parametersOf(args.firstName) }

    private val pickMedia = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                getRealPathFromUri(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    profileViewModel.profile.collect {profile ->
                        profile?.let {
                            updateUi(it)
                        }
                    }
                }
            }


            backButton.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.backToSignin()
                )
                viewModelStore.clear()
            }

            changePhoto.setOnClickListener {
                pickMedia.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }

            logOutImg.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.backToSignin()
                )
            }

            bottomNavigation.apply {
                selectedItemId = R.id.profile

                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.home -> {
                            findNavController().navigate(
                                ProfileFragmentDirections.tabBarShowPage1(args.firstName)
                            )

                            true
                        }
                        else -> false
                    }
                }
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.bottomNavigation.menu.findItem(R.id.profile).isChecked = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(profile: Profile) {
        binding.apply {
            val fullName = "${profile.firstName} ${profile.lastName}"
            profileName.text = fullName

            if (profile.photoFileBitmap != null) {
                profilePhoto.setImageBitmap(profile.photoFileBitmap)
            }
            val balance = "$ ${profile.balance}"
            balanceValue.text = balance
        }
    }

    private fun getRealPathFromUri(contentUri: Uri) {
        val proj = arrayOf(MediaStore.Images.Media.DATA)

        val queryCursor = requireActivity().contentResolver
            .query(contentUri, proj,null,null,null)

        queryCursor?.use { cursor->
            if (cursor.moveToFirst()) {
                val imagePath = cursor.getString(0)

                binding.profilePhoto.doOnLayout {measuredView ->
                    val scaledBitmap = getScaleBitMap(
                        imagePath,
                        measuredView.width,
                        measuredView.height
                    )

                    binding.profilePhoto.setImageBitmap(scaledBitmap)

                    profileViewModel.updateProfile { oldProfile ->
                        oldProfile.copy(photoFileBitmap = scaledBitmap)
                    }
                }
            }
        }
    }
}