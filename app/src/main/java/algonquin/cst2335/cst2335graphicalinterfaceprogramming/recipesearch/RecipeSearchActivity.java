package algonquin.cst2335.cst2335graphicalinterfaceprogramming.recipesearch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import algonquin.cst2335.cst2335graphicalinterfaceprogramming.R;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.RecipeSearchActivityBinding;


public class RecipeSearchActivity extends AppCompatActivity {

    RecipeSearchActivityBinding binding = null;

    SpecificRecipeDao dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SpecificRecipeDatabase db = Room.databaseBuilder(getApplicationContext(),
                SpecificRecipeDatabase.class, "SpecificRecipeDB").build();
        this.dao = db.specificRecipeDao();

        this.binding = RecipeSearchActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.recipetoolbar);

        // RequestHandle requires context before using queue
        RequestHandle.setContext(this);

        // startup fragment
        setFragment(new RecipeSearchFragment(), true);
    }

    public void setFragment(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.recipe_fragmentContainerView, fragment);
        if (addToBackstack) t.addToBackStack(null);
        t.commit();
    }


    public SpecificRecipeDao getSpecificRecipeDao(){
        return this.dao;
    }

}