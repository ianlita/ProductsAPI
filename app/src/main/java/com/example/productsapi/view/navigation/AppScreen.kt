package com.example.productsapi.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.productsapi.data.model.Category
import com.example.productsapi.view.screen.RecipeItemScreen
import com.example.productsapi.view.screen.RecipeScreen
import com.example.productsapi.viewmodel.MainViewModel

@Composable
fun AppScreen(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Route.RecipeScreen.route) {
        composable(route = Route.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToItem = {
                /*responsible from passing the category to the next screen*/
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(Route.RecipeItemScreen.route)
            })
        }
        composable(route = Route.RecipeItemScreen.route) {
            //get the category from the previous screen
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                ?: Category("", "", "", "")
            RecipeItemScreen(category = category)
        }
    }
}






