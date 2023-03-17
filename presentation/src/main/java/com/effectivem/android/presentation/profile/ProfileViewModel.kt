package com.effectivem.android.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.usecases.GetProfileUseCase
import com.effectivem.android.domain.usecases.UpdateProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val firstName: String,
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : ViewModel() {
    private val _profile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profile: StateFlow<Profile?> = _profile.asStateFlow()

    init {
        viewModelScope.launch {
            _profile.value = getProfileUseCase(firstName)
        }
    }

    fun updateProfile (onUpdate: (Profile) -> Profile) {
        _profile.update { oldProfile ->
            oldProfile?.let { onUpdate(it) }
        }

        profile.value?.let {
            viewModelScope.launch {
                updateProfileUseCase(it)
            }
        }
    }
}