package com.effectivem.android.presentation.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.effectivem.android.presentation.R
import com.effectivem.android.presentation.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            showPassword.setOnClickListener {
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                showPassword.visibility = View.GONE
                hidePassword.visibility = View.VISIBLE
            }

            loginButton.setOnClickListener {
                if(firstName.text.isBlank() || password.text.isBlank()) {
                    wrongNameOrPassword.setText(R.string.all_fields_should_be_filled)
                    wrongNameOrPassword.visibility = View.VISIBLE
                } else {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            loginViewModel.getProfile(firstName.text.toString())
                            if (loginViewModel.profile.value == null) {
                                wrongNameOrPassword.setText(R.string.wrong_name_or_password)
                                wrongNameOrPassword.visibility = View.VISIBLE
                            } else {
                                findNavController().navigate(
                                    LoginFragmentDirections.openProfile(
                                        firstName.text.toString()
                                    )
                                )
                                firstName.text.clear()
                                password.text.clear()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}