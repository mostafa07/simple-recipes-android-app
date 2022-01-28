package com.example.android.simplerecipes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.simplerecipes.data.model.domain.Recipe

class RecipeDetailViewModel : ViewModel() {

    private val _recipe: MutableLiveData<Recipe> = MutableLiveData()
    val recipe: LiveData<Recipe>
        get() = _recipe

    fun setRecipe(recipe: Recipe) {
        _recipe.postValue(recipe)
    }
}