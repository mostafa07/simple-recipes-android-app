package com.example.android.simplerecipes.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.simplerecipes.data.database.getDatabase
import com.example.android.simplerecipes.data.model.domain.Recipe
import com.example.android.simplerecipes.data.model.source.local.toDomainModel
import com.example.android.simplerecipes.data.model.source.remote.toDatabaseModel
import com.example.android.simplerecipes.network.RecipesWebService
import com.example.android.simplerecipes.network.builder.RetrofitServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesRepository(context: Context) {

    private val recipesWebService =
        RetrofitServiceBuilder.buildService(RecipesWebService::class.java)
    private val database = getDatabase(context.applicationContext)

    val recipes: LiveData<List<Recipe>> = Transformations.map(database.recipeDao.getAllRecipes()) {
        it.toDomainModel()
    }

    suspend fun getRecipes() {
        withContext(Dispatchers.IO) {
            val recipes = recipesWebService.getAllRecipesAsync().await()
            database.recipeDao.insertAll(*recipes.toDatabaseModel())
        }
    }
}