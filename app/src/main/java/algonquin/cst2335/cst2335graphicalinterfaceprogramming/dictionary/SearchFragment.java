package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.cst2335graphicalinterfaceprogramming.R;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.DictSearchFragmentBinding;

/**
 * A fragment responsible for handling search functionality in the Dictionary application.
 */
public class SearchFragment extends Fragment {

    DictionaryActivity activity = null;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The created view or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.activity = (DictionaryActivity) getActivity();

        SharedPreferences prefs = this.activity.getSharedPreferences("dictionary", Context.MODE_PRIVATE);
        final String search = prefs.getString("search", "");

        DictSearchFragmentBinding binding = DictSearchFragmentBinding.inflate(inflater);

        binding.dictSearchBar.setText(search);

        binding.dictHelpButton.setOnClickListener((view) -> {
            new AlertDialog.Builder(getContext())
                    .setTitle(getString(R.string.help_title))
                    .setMessage(getString(R.string.help_info))
                    .setNeutralButton("Ok", (dialog, i) -> dialog.dismiss())
                    .show();
        });

        binding.dictSearchButton.setOnClickListener((view) -> {

            final String input = binding.dictSearchBar.getText().toString();

            prefs.edit()
                    .putString("search", input)
                    .apply();

            AlertDialog errorDialog = new AlertDialog.Builder(getContext())
                    .setTitle("Error")
                    .setNeutralButton("Ok", (dialog, i) -> dialog.dismiss())
                    .create();

            final String url = String.format("https://api.dictionaryapi.dev/api/v2/entries/en/%s", input);
            RequestHandle.getQueue().add(new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,

                // on successful request
                    // nested loops to pull the information to be displayed as a dictionary search
                (rootArray) -> {
                    try {
                        final List<Definition> words = new ArrayList<>();

                        Log.i("Search", "Success");
                        for (int i = 0; i < rootArray.length(); ++i) {
                            final JSONObject wordObject = rootArray.getJSONObject(i);

                            final JSONArray meaningsArray = wordObject.getJSONArray("meanings");
                            for (int j = 0; j < meaningsArray.length(); j++) {
                                final JSONObject meaningObject = meaningsArray.getJSONObject(j);
                                final String partOfSpeech = meaningObject.getString("partOfSpeech");
                                final JSONArray defArray = meaningObject.getJSONArray("definitions");
                                for (int k = 0; k < defArray.length(); k++) {
                                    final JSONObject defObject = defArray.getJSONObject(k);
                                    final String definition = defObject.getString("definition");
                                    words.add(new Definition(input, definition, partOfSpeech));
                                }
                            }

                        }
                        this.activity.setFragment(new DefinitionsListFragment(input, words), true);
                    } catch (Exception e) {
                        errorDialog.setMessage(e.getLocalizedMessage());
                        errorDialog.show();
                    }


                },

                // on error
                (error) -> {
                    errorDialog.setMessage(error.getLocalizedMessage());
                    errorDialog.show();
                }
            ));
        });

        // on click lister to store or delete words in the database.
        binding.dictSavedButton.setOnClickListener(view -> {
//            ArrayList<Word> words = new ArrayList<>();
//            words.add(new Word("a"));
//            words.add(new Word("b"));
//            words.add(new Word("c"));
            final DefinitionDao dao = this.activity.getDefinitionDao();
            AsyncTask.execute(()-> {
                List<String> words = dao.getAllWords();
                List<Word> defs = new ArrayList<>();
                for (String w : words){
                    final List<Definition> defs2 = dao.getDefinitionsForWord(w);
                    final Word word = new Word(w);
                    word.setDefinitions(defs2);
                    defs.add(word);
                }
                this.activity.setFragment(new SavedWordsFragment(defs), true);
            });
        });

        return binding.getRoot();

    }

}
