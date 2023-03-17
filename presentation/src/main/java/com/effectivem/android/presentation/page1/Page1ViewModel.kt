package com.effectivem.android.presentation.page1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivem.android.domain.entities.LatestItem
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.entities.SaleItem
import com.effectivem.android.domain.usecases.GetLatestItemsUseCase
import com.effectivem.android.domain.usecases.GetProfileUseCase
import com.effectivem.android.domain.usecases.GetSaleItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "Page1ViewModel"

class Page1ViewModel(
    private val firstName: String,
    private val getProfileUseCase: GetProfileUseCase,
    private val getLatestItemsUseCase: GetLatestItemsUseCase,
    private val getSaleItemsUseCase: GetSaleItemsUseCase
) : ViewModel() {
    private val _profile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profile: StateFlow<Profile?> = _profile.asStateFlow()

    private val _latestItems =
        MutableStateFlow<List<LatestItem>>(emptyList())
    val latestItem
        get() = _latestItems.asStateFlow()

    private val _saleItems =
        MutableStateFlow<List<SaleItem>>(emptyList())
    val saleItems: StateFlow<List<SaleItem>>
        get() = _saleItems.asStateFlow()

    init {
        viewModelScope.launch {
            _profile.value = getProfileUseCase(firstName)

            try {
                val latestItems = getLatestItemsUseCase()
                Log.d(TAG, "Response received: $latestItems")
                _latestItems.value = latestItems
                val saleItems = getSaleItemsUseCase()
                Log.d(TAG, "Response received: $saleItems")
                _saleItems.value = saleItems
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch items", ex)
            }
        }
    }
}