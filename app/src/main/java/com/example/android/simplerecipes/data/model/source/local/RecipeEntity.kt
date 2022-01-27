package com.example.android.simplerecipes.data.model.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.simplerecipes.data.model.domain.Recipe

@Entity(tableName = "RECIPE")
data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val fats: String,
    val name: String,
    val image: String,
    val carbohydrates: String,
    val fibers: String,
    val calories: String,
    val headline: String,
    val proteins: String,
    val description: String,
    val ingredients: List<String>,
    val incompatibilities: List<String>?
)

fun List<RecipeEntity>.toDomainModel(): List<Recipe> {
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