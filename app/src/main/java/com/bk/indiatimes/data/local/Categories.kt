package com.bk.indiatimes.data.local

import androidx.compose.runtime.mutableStateListOf
import com.bk.indiatimes.data.model.CategoryModel

object Categories {
    val categoriesList = mutableStateListOf(
        CategoryModel("All", "all"),
        CategoryModel("National", "national"),
        CategoryModel("Business", "business"),
        CategoryModel("Sports", "sports"),
        CategoryModel("World", "world"),
        CategoryModel("Politics", "politics"),
        CategoryModel("Technology", "technology"),
        CategoryModel("Startup", "startup"),
        CategoryModel("Entertainment", "entertainment"),
        CategoryModel("Miscellaneous", "miscellaneous"),
        CategoryModel("Hatke", "hatke"),
        CategoryModel("Science", "science"),
        CategoryModel("Automobile", "automobile")
    )
}
