package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.RecipeMessage;

@Dao
public interface RecipeMessageDAO {
    @Insert
    void insertRecipe(RecipeMessage e);

    @Query("Select * from RecipeMessage")
    List<RecipeMessage> getAllRecipes();

    @Query("Delete from RecipeMessage")
    void delete();

    @Delete
    void deleteRecipe(RecipeMessage e);
}
