package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.myapplication.R;
import com.example.myapplication.databinding.SavedRecipesBinding;
import com.example.myapplication.databinding.RecipeFavoriteBinding;
import com.example.myapplication.SavedRecipesViewModel;
public class SavedRecipes {
    RecipeFavoriteBinding recipeLayout;
    SavedRecipesViewModel savedModel;
    SavedRecipesBinding binding;
    RecipeMessageDAO emDAO;
    Executor thread;
    List<RecipeMessage> recipesFavorites = new ArrayList<>();
    RecyclerView.Adapter myAdapter;
    int position;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(this, RecipeMain.class);
                startActivity(intent);
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = SavedRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerFav.setLayoutManager(new LinearLayoutManager(this));
        thread = Executors.newSingleThreadExecutor();
        RecipeDatabase db = Room.databaseBuilder(getApplicationContext(),RecipeDatabase.class, "database-name").build();

        emDAO = db.emDAO();
        super.onCreate(savedInstanceState);
        savedModel = new ViewModelProvider(this).get(SavedRecipesViewModel.class);
        recipesFavorites = savedModel.recipes.getValue();
        if (recipesFavorites == null){
            savedModel.recipes.postValue(recipesFavorites = new ArrayList<>());
            thread.execute(()-> {
                recipesFavorites.addAll(emDAO.getAllRecipes());

                runOnUiThread(() -> binding.recyclerFav.setAdapter(myAdapter));
            });

        }

        setSupportActionBar(binding.toolbar2);

        savedModel.selectedMessage.observe(this,(recipeMessage -> {
            RecipeDetailsFragment recipeFragment = new RecipeDetailsFragment(recipeMessage);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, recipeFragment)
                    .addToBackStack("").commit();
        }));


        binding.recyclerFav.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                recipesLayout = RecipeFavoriteBinding.inflate(getLayoutInflater());
                return new MyRowHolder(recipesLayout.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.recipe.setText(recipesFavorites.get(position).getRecipe());
                holder.deleteRecipe.setOnClickListener(delete->{

                    AlertDialog.Builder builder = new AlertDialog.Builder(SavedRecipes.this);
                    builder.setMessage(getResources().getString(R.string.delete_message) + holder.recipe.getText())
                            .setTitle(getResources().getString(R.string.question))
                            .setNegativeButton("No", (dialog, cl)->{})
                            .setPositiveButton(getResources().getString(R.string.yes) , (dialog, cl)->{
                                RecipeMessage del = recipesFavorites.get(position);
                                thread = Executors.newSingleThreadExecutor();
                                thread.execute(()->{
                                    emDAO.deleteRecipe(del);
                                });
                                recipesFavorites.remove(position);
                                myAdapter.notifyItemRemoved(position);
                                Snackbar.make(binding.recyclerFav, getResources().getString(R.string.delete_success) +
                                                holder.recipe.getText() + getResources().getString(R.string.delete_success2),
                                        Snackbar.LENGTH_LONG).setAction(getResources().getString(R.string.undo), undo ->{
                                    thread.execute(()->{
                                        emDAO.insertRecipe(del);
                                    });
                                    recipesFavorites.add(position, del);
                                    myAdapter.notifyItemInserted(position);
                                }).show();



                            }).create().show();
                });
            }
            @Override
            public int getItemCount() {
                return recipesFavorites.size();
            }
        });
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView recipe;
        TextView summary;
        ImageView deleteRecipe;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(clk->{
                position = getAdapterPosition();
                RecipeMessage selected = recipesFavorites.get(position);
                savedModel.selectedMessage.postValue(selected);

            });
            recipe = itemView.findViewById(R.id.recipe);
            summary = itemView.findViewById(R.id.location);
            deleteRecipe = itemView.findViewById(R.id.deleteTicket);



        }
    }

}
