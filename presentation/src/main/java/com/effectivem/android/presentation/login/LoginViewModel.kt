package com.effectivem.android.presentation.login

import androidx.lifecycle.ViewModel
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.usecases.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    private val _profile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profile: StateFlow<Profile?> = _profile.asStateFlow()

    suspend fun getProfile(firstName: String) {
        _profile.value = getProfileUseCase(firstName)
    }
}