package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import algonquin.cst2335.cst2335graphicalinterfaceprogramming.R;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.DictDefinitionsListFragmentBinding;

/**
 * A fragment class representing a list of definitions for a given word.
 * It includes functionality for displaying and interacting with a list of definitions,
 * allowing users to save and delete word definitions.
 */
public class DefinitionsListFragment extends Fragment {

    final List<Definition> defs;
    final String word;

    DictionaryActivity activity = null;

    /**
     * Constructs a new DefinitionsListFragment with the specified word and list of definitions.
     *
     * @param word The word for which the definitions are displayed.
     * @param defs The list of definitions to be displayed.
     */
    public DefinitionsListFragment(String word, List<Definition> defs) {
        this.defs = defs;
        this.word = word;
    }

    /**
     * Called to create the view hierarchy associated with the fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate views.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The root view of the fragment's layout.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.activity = (DictionaryActivity) getActivity();
        DefinitionDao dao = this.activity.getDefinitionDao();

        DictDefinitionsListFragmentBinding binding = DictDefinitionsListFragmentBinding.inflate(inflater);
        binding.dictWordTitle.setText(word.toUpperCase());

        binding.dictToggleSaved.setOnClickListener(view -> {
            AsyncTask.execute(()-> {
                final List<Definition> existing = dao.getDefinitionsForWord(this.word);
                if (existing.size() > 0) {
                    dao.deleteDefinitionsForWord(this.word);
                    Snackbar.make(getContext(), binding.getRoot(), "Saved word deleted.", Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> {
                            AsyncTask.execute(()-> dao.insertAll(existing));
                        })
                        .show();
                }
                else {
                    dao.insertAll(this.defs);
                    Snackbar.make(getContext(), binding.getRoot(), "Word saved.", Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> {
                            AsyncTask.execute(()-> dao.deleteDefinitionsForWord(this.word));
                        })
                        .show();
                }
            });
        });

        binding.dictRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.dictRecyclerView.setAdapter(new RecyclerView.Adapter<DefinitionsListFragment.ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.dict_definition_fragment, parent, false);
                ViewHolder vh = new ViewHolder(v);
                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.numberText.setText(Integer.toString(position + 1));
                final Definition d = DefinitionsListFragment.this.defs.get(position);
                holder.partOfSpeech.setText(d.getPartOfSpeech());
                holder.defParagraph.setText(d.getDefinition());
            }

            @Override
            public int getItemCount() {
                final DefinitionsListFragment th1s = DefinitionsListFragment.this;
                return th1s.defs.size();
            }
        });

        return binding.getRoot();
    }

    /**
     * A ViewHolder for the RecyclerView to hold the views associated with a single item in the list of definitions.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView numberText;
        public final TextView partOfSpeech;
        public final TextView defParagraph;

        /**
         * Constructs a new ViewHolder with the given item view.
         *
         * @param itemView The view representing a single item in the RecyclerView.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.numberText = itemView.findViewById(R.id.dict_defNumber);
            this.partOfSpeech = itemView.findViewById(R.id.dict_defPartOfSpeech);
            this.defParagraph = itemView.findViewById(R.id.dict_defParagraph);
        }
    }
}