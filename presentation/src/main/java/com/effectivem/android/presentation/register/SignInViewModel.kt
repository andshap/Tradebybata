package com.effectivem.android.presentation.register

import androidx.lifecycle.ViewModel
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.usecases.AddProfileUseCase
import com.effectivem.android.domain.usecases.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val addProfileUseCase: AddProfileUseCase
) : ViewModel() {
    private val _profile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profile: StateFlow<Profile?> = _profile.asStateFlow()

    suspend fun getProfile(firstName: String) {
        _profile.value = getProfileUseCase(firstName)
    }

    suspend fun addProfile(profile: Profile) {
        addProfileUseCase(profile)
    }
}