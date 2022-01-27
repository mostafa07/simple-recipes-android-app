package com.example.android.simplerecipes.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.simplerecipes.data.model.source.local.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RECIPE")
    fun getAllRecipes(): LiveData<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg recipes: RecipeEntity)
}