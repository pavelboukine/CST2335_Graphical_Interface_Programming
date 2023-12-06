package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
public class RecipeMainViewModel extends ViewModel {
    public MutableLiveData<ArrayList<RecipeMessage>> events = new MutableLiveData<>();
    public MutableLiveData<RecipeMessage> selectedMessage = new MutableLiveData<>();

}
