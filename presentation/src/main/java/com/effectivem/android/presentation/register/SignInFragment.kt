package com.effectivem.android.presentation.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.presentation.MainActivity
import com.effectivem.android.presentation.R
import com.effectivem.android.presentation.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var newProfile = Profile(
            id = UUID.randomUUID(),
            firstName = "",
            lastName = "",
            email = "",
            password = "",
            balance = 0
        )

        binding.apply {
            firstName.doOnTextChanged { text, _, _, _ ->
                profileExists.visibility = View.GONE
                newProfile = newProfile.copy(firstName = text.toString())
            }

            lastName.doOnTextChanged { text, _, _, _ ->
                profileExists.visibility = View.GONE
                newProfile = newProfile.copy(lastName = text.toString())
                newProfile = newProfile.copy(password = text.toString())
            }

            email.doOnTextChanged { text, _, _, _ ->
                if (text!!.isNotBlank() && !isEmailValid(text.toString())) {
                    emailIsNotInFormat.visibility = View.VISIBLE
                } else {
                    emailIsNotInFormat.visibility = View.GONE
                    newProfile = newProfile.copy(email = text.toString())
                }
            }

            signInButton.setOnClickListener {
                if(firstName.text.isBlank() || lastName.text.isBlank() ||
                    email.text.isBlank()) {
                    profileExists.setText(R.string.all_fields_should_be_filled)
                    profileExists.visibility = View.VISIBLE
                } else if (!isEmailValid(email.text.toString())) {
                    emailIsNotInFormat.visibility = View.VISIBLE
                } else {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            signInViewModel.getProfile(firstName.text.toString())
                            if (signInViewModel.profile.value != null) {
                                profileExists.setText(R.string.profile_exists)
                                profileExists.visibility = View.VISIBLE
                            } else {
                                signInViewModel.addProfile(newProfile)
                                findNavController().navigate(
                                    SignInFragmentDirections.openProfile(
                                        firstName.text.toString()
                                    )
                                )
                                firstName.text.clear()
                                lastName.text.clear()
                                email.text.clear()
                            }
                        }
                    }
                }
            }

            logIn.setOnClickListener {
                findNavController().navigate(
                    SignInFragmentDirections.openLogin()
                )
                firstName.text.clear()
                lastName.text.clear()
                email.text.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}