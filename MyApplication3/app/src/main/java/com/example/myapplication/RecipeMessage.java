package com.example.myapplication;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
public class RecipeMessage {

    @ColumnInfo(name="recipeId")
    private String recipeId;
    @ColumnInfo(name="recipe")
    protected String recipe;

    @ColumnInfo(name="URL")
    protected String URL;

    @Ignore
    protected Bitmap img;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    public RecipeMessage(){

    }

    public RecipeMessage(String re, String sum, String URL, Bitmap img, String recipeId){
        this.recipe = re;
        this.summary = sum;
        this.URL = URL;
        this.img = img;
        this.setRecipeId(recipeId);

    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

}
