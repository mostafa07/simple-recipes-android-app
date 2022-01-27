package com.example.android.simplerecipes.data.model.source.remote

import com.example.android.simplerecipes.data.model.domain.Recipe
import com.example.android.simplerecipes.data.model.source.local.RecipeEntity
import com.google.gson.annotations.SerializedName

data class RecipeNetworkModel(
    val id: String,
    val fats: String,
    val name: String,
    val time: String,
    val image: String,
    val weeks: List<String>,
    @SerializedName("carbos")
    val carbohydrates: String,
    val fibers: String,
    val rating: Double?,
    val country: String,
    val ratings: Long?,
    val calories: String,
    val headline: String,
    val keywords: List<String>,
    val products: List<String>,
    val proteins: String,
    val favorites: Long,
    val difficulty: Long,
    val description: String,
    val highlighted: Boolean,
    val ingredients: List<String>,
    val incompatibilities: List<String>?,
    @SerializedName("deliverable_ingredients")
    val deliverableIngredients: List<String>,
    @SerializedName("undeliverable_ingredients")
    val undeliverableIngredients: List<String>?
)

fun List<RecipeNetworkModel>.toDomainModel(): List<Recipe> {
    return map {
        Recipe(
            id = it.id,
            fats = it.fats,
            name = it.name,
            image = it.image,
            carbohydrates = it.carbohydrates,
            fibers = it.fibers,
            calories = it.calories,
            headline = it.headline,
            proteins = it.proteins,
            description = it.description,
            ingredients = it.ingredients,
            incompatibilities = it.incompatibilities
        )
    }
}

fun List<RecipeNetworkModel>.toDatabaseModel(): Array<RecipeEntity> {
    return map {
        RecipeEntity(
            id = it.id,
            fats = it.fats,
            name = it.name,
            image = it.image,
            carbohydrates = it.carbohydrates,
            fibers = it.fibers,
            calories = it.calories,
            headline = it.headline,
            proteins = it.proteins,
            description = it.description,
            ingredients = it.ingredients,
            incompatibilities = it.incompatibilities
        )
    }.toTypedArray()
}