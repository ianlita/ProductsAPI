package com.example.productsapi.view.navigation

sealed class Route(val route: String) {
    object RecipeScreen : Route("recipescreen")
    object RecipeItemScreen : Route("detailscreen")
}