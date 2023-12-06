package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * This interface defines data access operations for managing definitions in a database.
 */
@Dao
public interface DefinitionDao {

    /**
     * Retrieves a list of all distinct words present in the 'Definition' table.
     *
     * @return A list of unique words.
     */
    @Query("SELECT distinct word FROM Definition")
    List<String> getAllWords();

    /**
     * Retrieves a list of definitions for a specific word from the 'Definition' table.
     *
     * @param word The word for which definitions are to be retrieved.
     * @return A list of Definition objects associated with the given word.
     */
    @Query("SELECT * FROM Definition WHERE word =:word")
    List<Definition> getDefinitionsForWord(String word);

    /**
     * Inserts a list of Definition objects into the 'Definition' table.
     *
     * @param defs The list of Definition objects to be inserted.
     */
    @Insert
    void insertAll(List<Definition> defs);

    /**
     * Deletes all definitions associated with a specific word from the 'Definition' table.
     *
     * @param word The word for which definitions are to be deleted.
     */
    @Query("DELETE FROM Definition WHERE word =:word")
    void deleteDefinitionsForWord(String word);

}
