package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import algonquin.cst2335.cst2335graphicalinterfaceprogramming.R;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.DictDefinitionsListFragmentBinding;
import algonquin.cst2335.cst2335graphicalinterfaceprogramming.databinding.DictSavedWordsFragmentBinding;


/**
 * A fragment that displays a list of saved words.
 */
public class SavedWordsFragment extends Fragment {

    private DictionaryActivity activity = null;

    private final List<Word> words;

    /**
     * Constructs a new SavedWordsFragment with the given list of words.
     *
     * @param words List of Word objects to be displayed.
     */
    public SavedWordsFragment(List<Word> words) {
        this.words = words;
    }

    /**
     * Inflates the fragment's layout and initializes the UI components.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState The Bundle containing the saved state of the fragment.
     * @return The root View of the fragment's layout.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.activity = (DictionaryActivity) getActivity();

        DictSavedWordsFragmentBinding binding = DictSavedWordsFragmentBinding.inflate(inflater);

        binding.dictSavedRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.dictSavedRecycleview.setAdapter(new RecyclerView.Adapter<SavedWordsFragment.ViewHolder>() {

            /**
             * Creates a new ViewHolder when needed.
             *
             * @param parent   The ViewGroup into which the new View will be added.
             * @param viewType The type of the new View.
             * @return A new ViewHolder that holds a View of the given view type.
             */
            @NonNull
            @Override
            public SavedWordsFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.dict_word_fragment, parent, false);
                SavedWordsFragment.ViewHolder vh = new SavedWordsFragment.ViewHolder(v);
                return vh;
            }

            /**
             * Binds data to the ViewHolder.
             *
             * @param holder   The ViewHolder to bind data to.
             * @param position The position of the item within the adapter's data set.
             */
            @Override
            public void onBindViewHolder(@NonNull SavedWordsFragment.ViewHolder holder, int position) {
                final Word word = SavedWordsFragment.this.words.get(position);
                holder.wordText.setOnClickListener((view -> {
                    Log.i("Saved", "Clicked: " + word.getWord());
                    SavedWordsFragment.this.activity.setFragment(new DefinitionsListFragment(word.getWord(), word.getDefinitions()), true);
                }));
                holder.wordText.setText(word.getWord());
            }

            /**
             * Returns the total number of items in the data set held by the adapter.
             *
             * @return The total number of items in the adapter.
             */
            @Override
            public int getItemCount() {
                final SavedWordsFragment th1s = SavedWordsFragment.this;
                return th1s.words.size();
            }
        });

        return binding.getRoot();
    }

    /**
     * ViewHolder class for holding the view of each item in the RecyclerView.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordText;

        /**
         * Constructs a new ViewHolder with the given View.
         *
         * @param itemView The root view of the ViewHolder.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordText = itemView.findViewById(R.id.dict_wordText);
        }
    }
}