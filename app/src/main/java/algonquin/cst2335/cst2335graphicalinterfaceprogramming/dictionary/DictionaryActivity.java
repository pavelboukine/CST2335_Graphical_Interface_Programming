package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import algonquin.cst2335.cst2335graphicalinterfaceprogramming.R;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.DictMainLayoutBinding;

/**
 * The DictionaryActivity class serves as the main entry point for the dictionary application.
 * It is responsible for initializing the database, setting up the UI, and managing fragments.
 */
public class DictionaryActivity extends AppCompatActivity {

    DictMainLayoutBinding binding = null;

    DefinitionDao dao;

    /**
     * Called when the activity is first created. This method performs the initial setup
     * including database initialization, UI setup, and the launch of the initial fragment.
     *
     * @param savedInstanceState If non-null, this Bundle contains previously saved state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Database db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "DefinitionDB").build();
        this.dao = db.definitionDao();

        this.binding = DictMainLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setSupportActionBar(binding.dictToolbar);

        // RequestHandle requires context before using queue
        RequestHandle.setContext(this);

        // startup fragment
        setFragment(new SearchFragment(), false);
    }

    /**
     * Sets the given fragment in the fragment container view, optionally adding it to the back stack.
     *
     * @param fragment         The fragment to be displayed.
     * @param addToBackstack   If true, the fragment is added to the back stack.
     */
    public void setFragment(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.dict_fragmentContainerView, fragment);
        if (addToBackstack) t.addToBackStack(null);
        t.commit();
    }

    /**
     * Gets the DefinitionDao instance associated with the MainActivity.
     *
     * @return The DefinitionDao instance used for database operations.
     */
    public DefinitionDao getDefinitionDao(){
        return this.dao;
    }

}