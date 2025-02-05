package com.example.productsapi.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.productsapi.data.model.Category

@Composable
fun RecipeItemScreen(category: Category) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Category Name
        Text(
            text = category.strCategory,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Category Image
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(bottom = 16.dp)
        )

        // Category Description (Scrollable)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .weight(1f)
        ) {
            item {
                Text(
                    text = category.strCategoryDescription,
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecipeItemScreenPreview() {
    val dummyModel = Category(idCategory = "test",
    strCategory = "test",
    strCategoryThumb = "test",
    strCategoryDescription = "test"
    )
    RecipeItemScreen(dummyModel)
}