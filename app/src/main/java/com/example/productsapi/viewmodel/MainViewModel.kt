package com.example.productsapi.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.productsapi.data.api.service
import com.example.productsapi.data.model.RecipeState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = service.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                // Handle the exception here
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    error = "Error fetching categories: ${e.message}"
                )
            }
        }
    }
}