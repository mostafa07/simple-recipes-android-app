package com.example.android.simplerecipes.data.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
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
) : Parcelable