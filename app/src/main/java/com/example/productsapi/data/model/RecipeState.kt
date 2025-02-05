package com.example.productsapi.data.model

data class RecipeState(
    val isLoading: Boolean = false,
    val list: List<Category> = emptyList(),
    val error: String? = null

)
