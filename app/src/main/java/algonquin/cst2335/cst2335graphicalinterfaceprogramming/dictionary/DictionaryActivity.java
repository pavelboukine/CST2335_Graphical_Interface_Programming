package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import algonquin.cst2335.cst2335graphicalinterfaceprogramming.R;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.DictMainLayoutBinding;


public class DictionaryActivity extends AppCompatActivity {

    DictMainLayoutBinding binding = null;

    DefinitionDao dao;

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

    public void setFragment(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.dict_fragmentContainerView, fragment);
        if (addToBackstack) t.addToBackStack(null);
        t.commit();
    }

    public DefinitionDao getDefinitionDao(){
        return this.dao;
    }



}