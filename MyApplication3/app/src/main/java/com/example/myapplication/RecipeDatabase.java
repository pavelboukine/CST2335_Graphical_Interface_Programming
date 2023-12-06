package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.RecipeMessage;
import com.example.myapplication.RecipeMessageDAO;

@Database(entities = {RecipeMessage.class}, version = 2)
public abstract class RecipeDatabase extends RoomDatabase{
    public abstract RecipeMessageDAO emDAO();
}
