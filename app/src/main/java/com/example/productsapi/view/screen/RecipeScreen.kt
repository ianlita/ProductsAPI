package com.example.productsapi.view.screen


import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.productsapi.data.model.Category
import com.example.productsapi.data.model.RecipeState

@Composable
fun RecipeScreen(viewState: RecipeState, navigateToItem: (Category) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()
        .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        //when loading, then display spinner
        val modifier = Modifier
        when {
            viewState.isLoading -> {

                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text("An error occurred. Pleas try again")
            }
            viewState.list.isEmpty() -> {
                Text("No recipes found", textAlign = TextAlign.Center)
            }
            else -> {
                //display recipes
                CategoryScreen(viewState.list, navigateToItem)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToItem: (Category) -> Unit) {
    //implement lazy vertical grid
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Recipes",
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp),
            fontSize = 32.sp
        )
        //display recipes in a grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categories) {
                    category ->
                CategoryItem(category, navigateToItem)
            }
        }
    }

}

@Composable
fun CategoryItem(category: Category, navigateToItem: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigateToItem(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Category Image
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        // Category Name
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}