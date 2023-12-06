package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.RecipeInfoBinding;

public class RecipeDetailsFragment extends Fragment {
    RecipeMessage selected;

    public RecipeDetailsFragment(RecipeMessage e){selected = e;}

    @Override View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        RecipeInfoBinding binding = RecipeInfoBinding.inflate(inflater);
        binding.recipeSummary.setText(selected.text);
        binding.recipeURL.setText(selected.URL);
        binding.imgRecipe.setImageBitmap(selected.img);
        return binding.getRoot();

    }
}
