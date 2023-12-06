package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SavedRecipesViewModel extends ViewModel {
    public MutableLiveData<List<RecipeMessage>> recipes = new MutableLiveData<java.util.List<RecipeMessage>>();
    public MutableLiveData<RecipeMessage> selectedMessage = new MutableLiveData<>();

}
