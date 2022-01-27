package com.example.android.simplerecipes.network

import com.example.android.simplerecipes.data.model.source.remote.RecipeNetworkModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RecipesWebService {

    @GET(GET_ALL_RECIPES_END_POINT)
    fun getAllRecipesAsync(): Deferred<List<RecipeNetworkModel>>

    companion object {
        const val GET_ALL_RECIPES_END_POINT = "43427003d33f1f6b51cc"
    }
}