package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTicketMainBinding;
import com.example.myapplication.databinding.EventInfoBinding;
import com.example.myapplication.databinding.EventsDetailsBinding;
import com.example.myapplication.pexels_ui.PexelsMain;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
public class RecipeMain extends AppCompatActivity{

    RecipesDetailsBinding recipesLayout;
    ActivityRecipeMainBinding binding;
    SharedPreferences sharedPreferences;
    String recipe;
    RequestQueue queue = null;
    RecyclerView.Adapter myAdapter;
    RecipeMessage recipeMessage;
    ArrayList<RecipeMessage> recipeMessageArrayList;
    RecipeMainViewModel recipeModel;
    Bitmap image;
    ImageRequest imgReq;
    int position;
    EventMessageDAO emDAO;
    Executor thread;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fav_recipes:
                Intent intent = new Intent(this, SavedRecipes.class);
                startActivity(intent);
                break;
            case R.id.about:
                Snackbar.make(binding.getRoot(), "Recipe Search app. Tony Chen" +
                        binding.searchBar.getText().toString(), Snackbar.LENGTH_LONG).show();
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
    protected void onCreate(Bundle savedInstanceState) {
        thread = Executors.newSingleThreadExecutor();
        RecipeDatabase db = Room.databaseBuilder(getApplicationContext(),RecipeDatabase.class, "database-name").build();
        emDAO = db.emDAO();

        super.onCreate(savedInstanceState);
        recipeMessage = new RecipeMessage();
        recipeModel = new ViewModelProvider(this).get(RecipeMainViewModel.class);
        recipeMessageArrayList = recipeModel.recipes.getValue();
        if(recipeMessageArrayList == null){
            recipeModel.recipes.postValue(recipeMessageArrayList = new ArrayList<>());
            thread.execute(()->{

                runOnUiThread(()-> binding.recyclerView.setAdapter(myAdapter));

            });
        }
        binding = ActivityRecipeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        queue = Volley.newRequestQueue(this);
        setSupportActionBar(binding.toolbar);

        sharedPreferences = getSharedPreferences("Recipe", Context.MODE_PRIVATE);

        binding.getEventsBtn.setOnClickListener(click -> {
            recipeMessageArrayList.clear();
            myAdapter.notifyDataSetChanged();
            recipe = binding.searchBar.getText().toString();

            String URL = null;
            try {
                URL = "https://api.spoonacular.com/recipes/complexSearch?query=" +
                        URLEncoder.encode(recipe, "UTF-8") +
                        "&apiKey=b77b74c596004d40bd0f4ec1400b4cd3";

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {
                        try {
                            JSONObject page = response.getJSONObject("page");
                            String totalElements = page.getString("totalElements");
                            JSONObject embedded = response.getJSONObject("_embedded");
                            JSONArray search = embedded.getJSONArray("recipes");
                            for (int i = 0; i < search.length(); i++){
                                recipeMessage = new RecipeMessage();
                                JSONObject eventIndex = search.getJSONObject(i);
                                String recipes = recipeIndex.getString("name");

                                /*for(int e = 0; e < images.length(); e++){

                                    int widthImg = indexImg.getInt("width");
                                    int heightImg = indexImg.getInt("height");
                                    if (widthImg == 100 && heightImg == 56){



                                    }
                                }*/

                                String pathname = getFilesDir() + "/" + recipeId + ".png";
                                File file = new File(pathname);
                                if (file.exists()){
                                    image = BitmapFactory.decodeFile(pathname);
                                }
                                else{
                                    imgReq = new ImageRequest(imgURL, response1 -> {
                                        try{
                                            image = response1;
                                            image.compress(Bitmap.CompressFormat.PNG, 100,
                                                    RecipeMain.this.openFileOutput(recipeId + ".png",
                                                            Activity.MODE_PRIVATE));

                                        } catch (FileNotFoundException e){
                                            e.printStackTrace();

                                        }
                                    }, 1024 , 1024, ImageView.ScaleType.CENTER,
                                            null, (error -> { }));

                                }

                                image = BitmapFactory.decodeFile(pathname);
                                queue.add(imgReq);

                                JSONArray summary = recipeIndex.getJSONArray("summaries");
                                for(int l = 0; l < summary.length(); l++){
                                    JSONObject recipeIndex = summary.getJSONObject(l);
                                    recipeMessage.setSummary(recipeIndex.getString("summary"));
                                }

                                runOnUiThread(()->{
                                    binding.results.setText(getResources().getString(R.string.totalRecipes) + totalElements);
                                    binding.results.setVisibility(View.VISIBLE);
                                    recipeMessage.setRecipe(recipe);
                                    recipeMessage.setImg(image);
                                    recipeMessage.setRecipeId(recipeId);
                                    recipeMessage.setURL("URL:" + recipeUrl);
                                    recipeMessageArrayList.add(recipeMessage);

                                });

                            }
                            myAdapter.notifyItemInserted(recipeMessageArrayList.size() - 1);


                        }catch (JSONException e){
                            e.printStackTrace();
                            Snackbar.make(binding.getRoot(), getResources().getString(R.string.no_recipes) +
                                    " " + binding.searchBar.getText().toString(), Snackbar.LENGTH_LONG).show();
                        }
                    },
                    (error -> { })

            );

            queue.add(request);


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Recipe", recipe);
            editor.apply();

        });

        recipeName = sharedPreferences.getString("Recipe", "");
        binding.searchBar.setText(recipe);

        recipeModel.selectedMessage.observe(this,(recipeMessage)->{
            RecipeDetailsFragment recipeFragment = new RecipeDetailsFragment(recipeMessage);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, recipeFragment)
                    .addToBackStack("").commit();

        });

        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                recipesLayout = RrecipesDetailsBinding.inflate(getLayoutInflater());
                return new MyRowHolder(recipesLayout.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, @SuppressLint("RecyclerView") int position) {


                holder.recipe.setText(recipeMessageArrayList.get(position).getRecipe());
                holder.summary.setText(recipeMessageArrayList.get(position).getSummary());
                holder.saveRecipe.setOnClickListener(clk ->{
                    RecipeMessage e = recipeMessageArrayList.get(position);

                    thread.execute(()->{
                        emDAO.insertRecipe(e);
                    });
                    Toast.makeText(RecipeMain.this, getResources().getString(R.string.saved_recipe), Toast.LENGTH_LONG).show();
                });
            }

            @Override
            public int getItemCount() {
                return recipeMessageArrayList.size();
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }
        });
    }

    class MyRowHolder extends RecyclerView.ViewHolder{
        TextView recipe;
        TextView summary;
        ImageView saveRecipe;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(clk ->{
                position = getAdapterPosition();
                RecipeMessage selected = recipeMessageArrayList.get(position);
                recipeModel.selectedMessage.postValue(selected);
            });
            recipe = itemView.findViewById(R.id.recipe);
            summary = itemView.findViewById(R.id.summary);
            saveRecipe = itemView.findViewById(R.id.deleteRecipe);
        }
    }
}
